package cn.lxt6.document.www.service.impl;

import cn.lxt6.document.www.dao.IDocDao;
import cn.lxt6.document.www.dao.enums.DocTypeEnum;
import cn.lxt6.document.www.dao.enums.JavaTypeEnum;
import cn.lxt6.document.www.dao.enums.ParTypeEnum;
import cn.lxt6.document.www.dao.pojo.po.Doc;
import cn.lxt6.document.www.dao.pojo.po.DocPar;
import cn.lxt6.document.www.service.ICashService;
import cn.lxt6.document.www.service.IDocService;
import cn.lxt6.document.www.util.FileUtil;
import cn.lxt6.document.www.util.JsonUtil;
import cn.lxt6.document.www.util.MyMap;
import cn.lxt6.document.www.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenzy
 * @description
 * @since 2020-03-19
 */
@Service
public class DocServiceImpl implements IDocService {
    Logger logger = LoggerFactory.getLogger(DocServiceImpl.class);
    @Autowired
    private IDocDao docDao;
    @Autowired
    private ICashService cashService;

    /**
     * 接口文档
     * @return
     */
    @Transactional
    @Override
    public String md2Doc(MultipartFile[] files) {
/*      String filePath="E:\\work\\wd\\alipay_api.html";
        File pathFile = new File(filePath);
        if (pathFile==null){
            return "无效路径！";
        }
        //获取文档对象
        List<Doc> docList = new ArrayList<>();
        if (pathFile.isFile()){
            docList.addAll(getDocList(readFile(pathFile)));
        }else {
            File[] fileList = pathFile.listFiles();
            if (fileList == null || fileList.length < 1) {
                return "没有找到文档文件！";
            }
            for (File file : fileList) {
                if (file.isFile()) {
                    docList.addAll(getDocList(readFile(file)));
                }
            }
        }*/

        /*获取文档对象*/
        List<Doc> docList = new ArrayList<>();
        try {
            for (MultipartFile file:files){
                docList.addAll(getDocList(new String(file.getBytes(),"UTF-8")));
            }
        } catch (IOException e) {
            logger.error("文件解析出错!");
            return "文件解析出错!";
        }
        if (docList.size() == 0) {
            return "没有读取到接口";
        }
        /*缓存中没有的接口才插入数据库*/
        List<MyMap> mapList = cashService.getNoCash(docList);
        if (mapList == null || mapList.size() < 1) {
            return "没有需要插入数据库的接口！";
        }
        /*插入缓存和数据库*/
        Integer dbCount = docDao.insertList(mapList);
        Integer cashCount = cashService.setDocList(mapList);
        if (!cashCount.equals(dbCount)) {
            String msg = StringUtil.join("插入数据库{}条，插入缓存{}条。数据：{}", dbCount,cashCount,JsonUtil.model2Str(mapList));
            logger.error(msg);
            /*回滚只会回滚数据库操作，redis缓存不会撤销，可能会存在bug*/
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚事务
            return msg;
        }
        return "执行成功-插入数：" + dbCount;
    }

    private List<Doc> getDocList(String fileContent) {
        List<Doc> docList = new ArrayList<>();

        if (StringUtil.isBlank(fileContent)) {
            return docList;
        }
        if (!fileContent.contains("<ul>")||!fileContent.contains("<hr")){
            return docList;
        }
        String[] docFiles = StringUtil.trimSpace(fileContent.trim())//.replaceAll("\\n", "")
                .split("<ul>",2)[1].split("<hr",2)[1].split("</blockquote>");
        if (docFiles == null || docFiles.length < 1) {
            return docList;
        }
        for (String docFile : docFiles) {
            String[] docs = docFile.split("<blockquote>");
            if (docs == null || docs.length != 2) {
                continue;
            }
                /*docs[0]包括:
                <h5><code>docName</code></h5>
                <p><code>GET</code><a>url<a/></p>
                <table><tbody><tr></tr></tbody></tbody></table>

                docs[1]包括：
  <p><code>参数例子</code></p>
  <ul><li><p>machineId 设备编号 String</p></li>
  </ul>
                * */
            String docName = getContentFirst(getContentFirst(docs[0], "h5"), "code");
            if (StringUtil.isBlank(docName)) {
                docName = getContentFirst(getContentFirst(docs[0], "h6"), "code");
                if (StringUtil.isBlank(docName)) {
                    docName = getContentFirst(getContentFirst(docs[0], "h4"), "code");
                }
            }
            String temp = getContentFirst(docs[0], "p");
            DocTypeEnum docType = DocTypeEnum.getDocType(getContentFirst(temp, "code").toLowerCase());
            String url = getContentFirst(temp, "a");
            if (StringUtil.isBlank(url)){
                continue;
            }
            Doc doc = new Doc();
            doc.setName(docName);
            doc.setDocType(docType);
            doc.setUrl(url);
            doc.setRoute(getRountByURL(url));
            //得到返回数据实例
            String[] resultContents = docs[1].split("<ul>");
            List<String> resultDateExamles = getContentList(resultContents[0].trim(), "code", "返回参数");
            if (resultDateExamles != null && resultDateExamles.size() > 0) {
                doc.setResultExample1(StringUtil.getContent(resultDateExamles.get(0).trim()));
                if (resultDateExamles.size() > 1) {
                    doc.setResultExample2(resultDateExamles.get(resultDateExamles.size()-1).trim());
                }
            }
            //得到返回参数
            if (resultContents.length==2){
                doc.setResultParList(getResultPar(resultContents[1].trim()));
            }
            //得到请求参数
            doc.setQuestParList(getQuestPar(docs[0].trim()));
            docList.add(doc);
        }
        return docList;
    }
    /*http://machinemanager.lxt6.cn:10085/zk_equipment_api/consumer/SetEquipmentInfo?code=XXX&amp;*/
    private String getRountByURL(String url){
        if (StringUtil.isBlank(url)){
            return null;
        }
        String rount=url.split("\\?")[0];
        if (rount.contains("//")){
            rount=rount.split("//")[1];
        }
        return rount.substring(rount.indexOf("/"));
    }

    private List<DocPar> getResultPar(String doc) {
        List<DocPar> resultParLi = new ArrayList<>();
        List<String> resultParList = getContentList(doc, "p");
        if (resultParList != null && resultParList.size() > 0) {
            for (String resultPar : resultParList) {
                if (StringUtil.isBlank(resultPar)) {
                    continue;
                }
                String[] resultPars = resultPar.split(" ");
                if (resultPars == null) {
                    continue;
                }
                DocPar docPar = new DocPar();
                if (resultPars.length == 1) {
                    docPar.setNotes(resultPars[0]);
                }
                if (resultPars.length >= 2) {
                    docPar.setName(resultPars[0]);
                    docPar.setDes(resultPars[1]);
                }
                if (resultPars.length >= 3) {
                    docPar.setDataType(JavaTypeEnum.getTypeByJavaType(resultPars[2]));
                }
                if (resultPars.length == 4) {
                    docPar.setNotes(resultPars[3]);
                }
                docPar.setParType(ParTypeEnum.QuestReturn);
                resultParLi.add(docPar);
            }
        }
        return resultParLi;
    }
    /*<tr><td>machineid</td><td>设备编号</td><td>String</td><td></td><td>Y</td><td>新设备皆是8字节，4字节的设备不进行出厂设置</td></tr>*/

    private List<DocPar> getQuestPar(String doc) {
        List<String> docParFiles = getContentList(doc, "tr");
        if (docParFiles == null || docParFiles.size() < 1) {
            return null;
        }

        List<DocPar> questPar = new ArrayList<>();
        for (String docParFile : docParFiles) {
            if (docParFile.contains("<td>参数名称</td>")) {
                continue;
            }

            List<String> columnContentList = getContentList(docParFile.replace("<td></td>", "<td> </td>"), "td");
            if (columnContentList == null || columnContentList.size() < 6) {
                continue;
            }
            if ("-".equals(columnContentList.get(0))){
                continue;
            }
            DocPar docPar = new DocPar();
            docPar.setName(columnContentList.get(0));
            docPar.setDes(columnContentList.get(1));
            docPar.setNotes(columnContentList.get(5));
            docPar.setDataType(JavaTypeEnum.getTypeByJavaType(columnContentList.get(2)));
            if (StringUtil.isNotBlank(columnContentList.get(3))) {
                docPar.setLength(Integer.valueOf(columnContentList.get(3)));
            }
            docPar.setIsMust("Y".equals(columnContentList.get(4).trim().toUpperCase()));
            docPar.setParType(ParTypeEnum.QuestPar);
            questPar.add(docPar);
        }
        return questPar;
    }

    private String getContentFirst(String source, String element) {
        List<String> result = getContentList(source, element);
        if (result.size() == 0) {
            return "";
        }
        return result.get(0).trim();
    }
    private List<String> getContentList(String source, String element, String noContent) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^>]*>" + "(.+?)</" + element + ">";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            if (r.contains(noContent)) {
                continue;
            }
            result.add(r);
        }
        return result;
    }

    private List<String> getContentList(String source, String element) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^>]*>" + "(.+?)</" + element + ">";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }
}

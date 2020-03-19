package cn.lxt6.document.www.service.impl;

import cn.lxt6.document.www.dao.IDocDao;
import cn.lxt6.document.www.dao.enums.DocTypeEnum;
import cn.lxt6.document.www.dao.enums.JavaTypeEnum;
import cn.lxt6.document.www.dao.enums.ParTypeEnum;
import cn.lxt6.document.www.dao.pojo.po.Doc;
import cn.lxt6.document.www.dao.pojo.po.DocPar;
import cn.lxt6.document.www.service.IDocService;
import cn.lxt6.document.www.util.JsonUtil;
import cn.lxt6.document.www.util.MyMap;
import cn.lxt6.document.www.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private IDocDao docDao;

    @Override
    public String md2Doc(String filePath) {
        File[] fileList = new File(filePath).listFiles();
        if (fileList!=null){
            List<MyMap> docList = new ArrayList<>();
            for (File file:fileList){
                if (file.isFile()){
                    docList.addAll(getDocList(file));
                }
            }
            if (docList.size()==0){
                return "没有读取到接口";
            }
            docDao.insertList(docList);
            return "执行成功";
        }
        return "没有找到文档文件！";
    }
    private List<MyMap> getDocList(File file) {
        String fileContent = readFile(file);
        if (StringUtil.isNotBlank()) {
            return null;
        }
        String[] docFiles = StringUtil.trimSpace(fileContent.trim()).replaceAll("\\n", "").split(" </blockquote>");
        if (docFiles == null || docFiles.length < 1) {
            return null;
        }
        List<MyMap> docList = new ArrayList<>();
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
            String docName = getContent(getContent(docs[0], "h5"), "code");
            if (docName.equals("[]")){
                docName=getContent(getContent(docs[0], "h6"), "code");
                if (docName.equals("[]")){
                    docName=getContent(getContent(docs[0], "h4"), "code");
                }
            }
            String temp = getContent(docs[0], "p");
            DocTypeEnum docType = DocTypeEnum.getDocType(getContent(temp, "code").toLowerCase());
            String url = getContent(temp, "a");
            Doc doc = new Doc();
            doc.setName(docName);
            doc.setDocType(docType);
            doc.setUrl(url);
            //得到返回数据实例
            String[] resultContents = docs[1].split("<ul>");
            List<String> resultDateExamles = getContentList(resultContents[0], "p","返回参数");
            if (resultDateExamles != null && resultDateExamles.size() > 0) {
                doc.setResultExample1(resultDateExamles.get(0));
                if (resultDateExamles.size() > 1) {
                    doc.setResultExample2(resultDateExamles.get(1));
                }
            }
            //得到返回参数
            doc.setResultParList(getResultPar(resultContents[1]));
            //得到请求参数
            doc.setQuestParList(getQuestPar(docs[0]));
            docList.add(JsonUtil.model2Map(doc));
        }
        return docList;
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
                if (resultPars==null){
                    continue;
                }
                DocPar docPar = new DocPar();
                if (resultPars.length==1){
                    docPar.setNotes(resultPars[0]);
                }
                if (resultPars.length>=2){
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

            List<String> columnContentList = getContentList(docParFile.replace("<td></td>","<td> </td>"), "td");
            if (columnContentList == null || columnContentList.size() < 6) {
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

    private String getContent(String source, String element) {
        List<String> result = getContentList(source, element);
        if (result.size() == 1) {
            return result.get(0);
        }
        return result.toString();
    }
    private List<String> getContentList(String source, String element,String noContent) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^>]*>" + "(.+?)</" + element + ">";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            if (r.contains(noContent)){
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
    public String readFile(File file) {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }
}

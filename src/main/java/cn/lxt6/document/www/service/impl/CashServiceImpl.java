package cn.lxt6.document.www.service.impl;

import cn.lxt6.document.www.dao.enums.DocTypeEnum;
import cn.lxt6.document.www.dao.pojo.po.Doc;
import cn.lxt6.document.www.service.ICashService;
import cn.lxt6.document.www.service.IRedisService;
import cn.lxt6.document.www.util.JsonUtil;
import cn.lxt6.document.www.util.MyMap;
import cn.lxt6.document.www.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzy
 * @description
 * @since 2020-03-20
 */
@Service
public class CashServiceImpl implements ICashService {
    @Autowired
    private IRedisService redisService;

    /**
     * 判断指定接口是否存储在redis中
     *
     * @param rount
     * @return
     */
    @Override
    public Boolean hasDoc(DocTypeEnum docTypeEnum, String rount) {
        String key = StringUtil.join("doc:{}_{}",docTypeEnum==null?"null":docTypeEnum.getValue(),rount);
        return redisService.hasKey(key);
    }

    /**
     * 得到没有缓存的redis
     * @param docList
     * @return
     */
    @Override
    public List<MyMap> getNoCash(List<Doc> docList) {
        if (docList == null || docList.size() < 1) {
            return null;
        }
        List<MyMap> resultList = new ArrayList<>();
        for (Doc doc : docList) {
            if (!hasDoc(doc.getDocType(), doc.getRoute())) {
                resultList.add(JsonUtil.model2Map(doc));
            }
        }
        return docList.size() < 1 ? null : resultList;
    }

    public Doc getDoc(DocTypeEnum docTypeEnum, String rount){
        String key = StringUtil.join("doc:{}_{}",docTypeEnum==null?"null":docTypeEnum.getValue(),rount);
        return redisService.get(key,Doc.class);
    }
    /**
     * 接口文档批量插入缓存，返回插入数量
     * @param docList
     * @return
     */
    @Override
    public Integer setDocList(List<Doc> docList) {
        Integer count = 0;
        if (docList==null||docList.size()<1){
            return count;
        }
        for (Doc doc:docList){
            String key = StringUtil.join("doc:{}_{}",doc.getDocType()==null?"null":doc.getDocType().getValue(),doc.getRoute());
            if (redisService.set(key,doc)){
                count++;
            }
        }
        return count;
    }

}

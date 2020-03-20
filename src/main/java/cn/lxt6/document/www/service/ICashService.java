package cn.lxt6.document.www.service;

import cn.lxt6.document.www.dao.enums.DocTypeEnum;
import cn.lxt6.document.www.dao.pojo.po.Doc;
import cn.lxt6.document.www.util.MyMap;

import java.util.List;

/**
 * @author chenzy
 * @description
 * @since 2020-03-20
 */
public interface ICashService {
    Boolean hasDoc(DocTypeEnum docTypeEnum, String docUrl);

    List<MyMap> getNoCash(List<Doc> docList);

    Integer setDocList(List<MyMap> mapList);
}

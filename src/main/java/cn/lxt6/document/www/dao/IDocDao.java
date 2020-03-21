package cn.lxt6.document.www.dao;

import cn.lxt6.document.www.dao.pojo.po.Doc;
import cn.lxt6.document.www.util.MyMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chenzy
 * @since 2020-03-19
 * @description
 */
public interface IDocDao {
    /*批量插入数据，并且返回插入的数据*/
    List<Doc> insertList(@Param("docList")List<MyMap> docList);

    List<Map> getList(@Param("whereMap") MyMap parMap);
}

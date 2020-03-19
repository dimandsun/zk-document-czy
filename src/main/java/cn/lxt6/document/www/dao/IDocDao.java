package cn.lxt6.document.www.dao;

import cn.lxt6.document.www.util.MyMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenzy
 * @since 2020-03-19
 * @description
 */
public interface IDocDao {
    Integer insertList(@Param("docList")List<MyMap> docList);
}

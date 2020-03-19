package cn.lxt6.document.www.service;

import cn.lxt6.document.www.util.MyMap;

/**
 * @author chenzy
 * @description
 * @since 2020-03-19
 */
public interface ICommonService {
    /**
     * 插入数据，
     * @param parMap
     * @return
     */
    public Integer insert(String tableName, MyMap parMap);
    /**
     * update数据，
     * @return
     */
    public Integer update(String tableName,MyMap setMap,MyMap whereMap);
    /**
     * 删除数据，
     * @param parMap
     * @return
     */
    public Integer delete(String tableName,MyMap parMap);
    /**
     * 查询数据，
     * @return
     */
    public Integer get(String tableName,String[] columns,MyMap whereMap);
}

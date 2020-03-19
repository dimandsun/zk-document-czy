package cn.lxt6.document.www.service.impl;

import cn.lxt6.document.www.dao.ICommonDao;
import cn.lxt6.document.www.service.ICommonService;
import cn.lxt6.document.www.util.MyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenzy
 * @description
 * @since 2020-03-19
 */
@Service
public class CommonServiceImpl implements ICommonService {
    @Autowired
    private ICommonDao commonDao;

    /**
     * 插入数据，
     * @param parMap
     * @return
     */
    public Integer insert(String tableName, MyMap parMap){
return commonDao.insert(tableName,parMap);
    }
    /**
     * update数据，
     * @return
     */
    public Integer update(String tableName,MyMap setMap,MyMap whereMap){
        return commonDao.update(tableName,setMap,whereMap);
    }
    /**
     * 删除数据，
     * @param parMap
     * @return
     */
    public Integer delete(String tableName,MyMap parMap){
        return commonDao.delete(tableName,parMap);
    }
    /**
     * 查询数据，
     * @return
     */
    public Integer get(String tableName,String[] columns,MyMap whereMap){
        return commonDao.get(tableName,columns,whereMap);
    }

}

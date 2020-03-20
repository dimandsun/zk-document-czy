package cn.lxt6.document.www.service.impl;

import cn.lxt6.document.www.service.IRedisService;
import cn.lxt6.document.www.util.JsonUtil;
import cn.lxt6.document.www.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Copyright©2017-2018 中卡科技 版权所有. All rights reserved.
 * @description redis操作工具实现类
 * @author Gypsophila
 * @date 2018/7/4
 */
@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;//redis操作模板

    @Override
    public Boolean hasKey(String key){
        if (StringUtil.isBlank(key)){
            return false;
        }
        return stringRedisTemplate.hasKey(key.trim());
    }

    /**
     */
    @Override
    public boolean set(String key, String value) {
        if (StringUtil.isBlank(key)){
            return false;
        }
        stringRedisTemplate.opsForValue().set(key,value);

        return true;
    }


    /**
     * @description 设置缓存时间，单位秒
     * @param key 键
     * @param value 值
     * @param timeout 过期时间
     * @return
     */
    @Override
    public boolean set(String key, String value, Long timeout) {
        if (StringUtil.isBlank(key)){
            return false;
        }
        stringRedisTemplate.opsForValue().set(key,value,timeout,TimeUnit.SECONDS);
        return true;
    }

    /**
     * 删除string数据
     * @param key
     * @return
     */
    @Override
    public boolean delete(String key) {
        if (StringUtil.isBlank(key)){
            return false;
        }
        stringRedisTemplate.delete(key);
        return true;
    }

    /**
     * 设置缓存时间，单位秒
     * @param key
     * @param value
     * @param timeout
     * @param <T>
     * @return
     */
    @Override
    public <T> boolean set(String key, T value, Long timeout) {
        if (StringUtil.isBlank(key)){
            return false;
        }
        stringRedisTemplate.opsForValue().set(key, JsonUtil.model2Str(value),timeout);
        return true;
    }

    /**
     * 把对象转换成json格式字符串，再存入redis
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    @Override
    public <T> boolean set(String key, T value) {
        if (StringUtil.isBlank(key)){
            return false;
        }
        stringRedisTemplate.opsForValue().set(key,JsonUtil.model2Str(value));
        return true;
    }

    /**
     * 读取缓存后，转换成指定对象
     * @param key
     * @param className
     * @param <T>
     * @return
     */
    @Override
    public <T> T get(String key, Class<T> className) {

        String josonS = this.get(key);
        return josonS==null?null:JsonUtil.str2Model(josonS,className);
    }
    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public String set(String key, List list) {
        return null;
    }

    /**
     * 裁剪redis指定数据
     * @param key
     * @param start 开始位置
     * @param end 结束位置
     * @return
     */
    @Override
    public boolean trimList(String key, long start, long end) {
        if (StringUtil.isBlank(key)){
            return false;
        }
        stringRedisTemplate.opsForList().trim(String.valueOf(key),end,start);
        return true;
    }

    /**
     * 获取list数据长度
     * @param key
     * @return
     */
    @Override
    public long getListSize(String key) {
        Long num = stringRedisTemplate.opsForList().size(key);
        if(num > 0){
            return num;
        }
        return 0L;
    }


    /**
     * @description 向一个已存在的缓存数据左侧插入新的数据
     */
    @Override
    public long leftPush(String key, String value) {
        if (StringUtil.isBlank(key)){
            return 0;
        }
        Long aLong = stringRedisTemplate.opsForList().leftPush(key, value);
        if(aLong > 0){
            return aLong;
        }
        return 0;
    }

    /**
     * 向一个已存在的缓存数据左侧插入新的数据
     */
    @Override
    public long rightPush(String key, String value) {
        if (StringUtil.isBlank(key)){
            return 0;
        }
        Long aLong = stringRedisTemplate.opsForList().leftPush(key, value);
        if(aLong > 0){
            return aLong;
        }
        return 0L;
    }

    /**
     * 插入数组
     */
    @Override
    public long pushArr(String key, String[] values) {
        Long aLong = stringRedisTemplate.opsForList().leftPushAll(key, values);
        if(aLong > 0){
            return aLong;
        }
        return 0;
    }

    /**
     * 插入集合
     * @param key
     * @param list
     * @param <T>
     * @return
     */
    @Override
    public <T>long pushList(String key,List<T> list) {
        Long aLong = stringRedisTemplate.opsForList().leftPushAll(key, list.toString());
        if(aLong > 0){
            return aLong;
        }
        return 0;
    }

    /**
     * @description 在列表中index的位置设置value值
     */
    @Override
    public boolean setListIndex(String key, long index, String value) {
        if (StringUtil.isBlank(key)){
            return false;
        }
        stringRedisTemplate.opsForList().set(key,index,value);
        return true;
    }

    /**
     * 指定list位置获取值
     * @param key
     * @param index 指定位置
     * @return
     */
    @Override
    public String get(String key, long index) {
        if (StringUtil.isBlank(key)){
            return null;
        }
        return stringRedisTemplate.opsForList().index(String.valueOf(key), index);
    }

    /**
     * 删除指定的哈希hashKeys，map的key：hashkeys
     * @param key
     * @param hashkeys
     * @return
     */
    @Override
    public boolean delHash(String key, Object ... hashkeys) {
        Long status = stringRedisTemplate.opsForHash().delete(key, hashkeys);
        if(status > 0){
            return true;
        }
        return false;
    }

    /**
     * 判断哈希hashKeys是否存在
     * @param key
     * @param hashkey
     * @return
     */
    @Override
    public boolean isHash(String key, Object hashkey) {
        return stringRedisTemplate.opsForHash().hasKey(key, hashkey);
    }

    /**
     * 获取key和hashKeys值
     * @param key
     * @param hashKeys
     * @return
     */
    @Override
    public List<Object> getHash(String key, Collection<Object> hashKeys) {
        return stringRedisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    @Override
    public Set<Object> getHashKey(String key) {
        return stringRedisTemplate.opsForHash().keys(key);
    }

    @Override
    public List getHashVal(String key) {
        return stringRedisTemplate.opsForHash().values(String.valueOf(key));
    }


    @Override
    public Map getHashAll(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * 存储Zet数据
     * @param key
     * @param map
     * @param score 排序规则
     * @return
     */
    @Override
    public boolean add(String key, Map<String, Object> map, Long score) {
        return stringRedisTemplate.opsForZSet().add(key, map.toString(), score);
    }

    /**
     * 存储Hash值
     * @param key
     * @param hashKey
     * @param hashValue
     * @return
     */
    @Override
    public boolean add(String key,Object hashKey,Object hashValue ) {
        if (StringUtil.isBlank(key)){
            return false;
        }
        stringRedisTemplate.opsForHash().put(key,hashKey,hashValue);
        return true;
    }


    @Override
    public Long rank(String key, Object o) {
//        stringRedisTemplate.opsForZSet().rangeByScore()
        return null;
    }


    /**
     * @description 通过排序负责返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     */
    @Override
    public Set rangeByScore(String key, Long min, Long max) {
        Set<String> strings = stringRedisTemplate.opsForZSet().rangeByScore(String.valueOf(key), min, max);
        return strings;
    }


    /**
     * 获取list值
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public List getList(String key, long start, long end) {
        if (StringUtil.isBlank(key)){
            return null;
        }
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取指定前缀的所有key
     * @param patternKey
     * @return
     */
    @Override
    public Set<String> keys(String patternKey){
        return stringRedisTemplate.keys(patternKey);
    }


    /**
     * @description 获取时间单位类型
     */
    public static TimeUnit getTimeUnitType(int timeUnitType){
        TimeUnit timeUnit = null;
        switch(timeUnitType)
        {
            case 1: timeUnit = TimeUnit.DAYS;//天
                break;
            case 2: timeUnit = TimeUnit.HOURS;//时
                break;
            case 3: timeUnit = TimeUnit.MINUTES;//分
                break;
            case 4: timeUnit = TimeUnit.SECONDS;//秒
                break;
            case 5: timeUnit = TimeUnit.MILLISECONDS;//毫秒
                break;
            case 6: timeUnit = TimeUnit.MICROSECONDS;//微秒
                break;
            case 7: timeUnit = TimeUnit.NANOSECONDS;//纳秒
                break;
        }
        return timeUnit;
    }


    public static void main(String[] args) {
        TimeUnit timeUnitType = getTimeUnitType(3);
        if(timeUnitType.equals(TimeUnit.MINUTES)){
            System.out.println("相等");
            System.out.println(timeUnitType);
            System.out.println(TimeUnit.MINUTES);
        }else {
            System.out.println("不相等");
        }

    }

}

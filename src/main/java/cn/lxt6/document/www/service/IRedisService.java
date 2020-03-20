package cn.lxt6.document.www.service;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Copyright©2017-2018 中卡科技 版权所有. All rights reserved.
 * @description redis接口
 * @author Gypsophila
 * @date 2018/7/3
 */
public interface IRedisService {


   Boolean hasKey(String key);

   boolean set(String key, String value);

   boolean set(String key, String value, Long timeout);

   boolean delete(String key);

   <T> boolean set(String key, T value, Long timeout);
   <T> boolean set(String key, T value);

   <T> T get(String key, Class<T> className);

    String get(String key);

   String set(String key, List list);

   boolean trimList(String key, long start, long end);

   long getListSize(String key);

   long leftPush(String key, String value);

   long rightPush(String key, String value);

   long pushArr(String key, String[] values);

   <T>long pushList(String key, List<T> list);

   boolean setListIndex(String key, long index, String value);

   String get(String key, long index);

   boolean delHash(String key, Object... hashkeys);

   boolean isHash(String key, Object hashkey);

   List<Object> getHash(String key, Collection<Object> hashKeys);

   Set<Object> getHashKey(String key);

   List getHashVal(String key);

   Map getHashAll(String key);

   boolean add(String key, Map<String, Object> map, Long score);

   boolean add(String key, Object hashKey, Object hashValue);

   Long rank(String key, Object o);

   Set rangeByScore(String key, Long min, Long max);

   List getList(String key, long start, long end);

   Set<String> keys(String patternKey);
}

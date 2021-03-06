package com.lv.zupu.base.redis;

import org.apache.ibatis.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/7/12 18:26
 * @Version: 1.0
 * modified by:
 */
@Component
public class StringRedisCache implements Cache<String,String> {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public String get(String var1) throws CacheException {
        return stringRedisTemplate.opsForValue().get(var1);
    }

    @Override
    public void set(String var1, String var2) throws CacheException {
        stringRedisTemplate.opsForValue().set(var1,var2);
    }

    @Override
    public void set(String var1, String var2, long expiretime, TimeUnit timeUnit) throws CacheException {
stringRedisTemplate.opsForValue().set(var1,var2,expiretime,timeUnit);
    }

    @Override
    public Boolean isExist(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public Boolean remove(String var1) throws CacheException {
        return stringRedisTemplate.delete(var1);
    }

    //追加某个key的值
    public Integer append(String k,String val){
        return stringRedisTemplate.opsForValue().append(k,val);
    }
    /**
     * boundValueOps 与opsForValue 操作相同
     * http://357029540.iteye.com/blog/2399036
     * @param key
     * @return
     */
    public Long boundValue(String key){
        stringRedisTemplate.opsForValue();
        return stringRedisTemplate.boundValueOps(key).increment(1);
    }

    public void hashMapPut(String key, Map<String,Object> mapVal){
        stringRedisTemplate.opsForHash().putAll(key,mapVal);
    }

    public Object hashMapGet(String key,String mapKey){
       return stringRedisTemplate.opsForHash().get(key,mapKey);
    }

    public Long leftPusList(String key,String...v){
       return stringRedisTemplate.opsForList().leftPushAll(key,v);
    }

    public String leftPop(String key){
       return stringRedisTemplate.opsForList().leftPop(key);
    }

    public List<String> listRange(String key, long start, long end){
        return stringRedisTemplate.opsForList().range(key,start,end);
    }

    public void listRemove(String key,long count, String value){
        stringRedisTemplate.opsForList().remove(key,count,value);
    }

}

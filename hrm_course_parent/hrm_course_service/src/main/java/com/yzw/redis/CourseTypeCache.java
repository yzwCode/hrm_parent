package com.yzw.redis;

import com.alibaba.fastjson.JSONArray;
import com.yzw.client.RedisClient;
import com.yzw.domain.CourseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseTypeCache {
    @Autowired
    private RedisClient redisClient;
    public static final String COURSETYPE_TO_CACHE = "coursetype_to_cache";
    /**
     * 从缓存中查询
     */
    public List<CourseType> getCourseTypesInCache(){
        //System.out.println("进入");
        //通过COURSETYPE_TO_CACHE拿到对应的字符串
        String s = redisClient.get(COURSETYPE_TO_CACHE);
        //在通过字符串转换框架将字符串解析为一个array数组
        return JSONArray.parseArray(s,CourseType.class);
    }
    public void setCourseTypesInCache(List<CourseType> courseTypeList){
        //先将传入的集合对象解析为字符串
        String s = JSONArray.toJSONString(courseTypeList);
        //将解析出来的字符串设置到缓存中
        redisClient.set(COURSETYPE_TO_CACHE,s);
    }
}

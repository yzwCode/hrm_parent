package com.yzw.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yzw.domain.CourseType;
import com.yzw.mapper.CourseTypeMapper;
import com.yzw.query.CourseTypeQuery;
import com.yzw.redis.CourseTypeCache;
import com.yzw.service.ICourseTypeService;
import com.yzw.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 课程目录 服务实现类
 * </p>
 *
 * @author yzw
 * @since 2019-08-31
 */
@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {
    @Autowired
    private CourseTypeMapper courseTypeMapper;
    @Autowired
    private CourseTypeCache courseTypeCache;
    @Override
    public PageList<CourseType> selectListPage(CourseTypeQuery query) {
        Page page = new Page(query.getPage(),query.getRows());
        List<CourseType> courseTypes = courseTypeMapper.selectListPage(page, query);
        return new PageList<>(page.getTotal(),courseTypes);
    }

    @Override
    public List<CourseType> queryAllTree(Long pid) {
        //System.out.println(pid+"-----");
        //通过courseTypeCache拿到缓存中数据
        List<CourseType> courseTypesInCache = courseTypeCache.getCourseTypesInCache();
        System.out.println(courseTypesInCache+"---------");
        //判断缓存中的数据是否存在
        if(courseTypesInCache==null||courseTypesInCache.size()<1){
            //不存在到数据库查
            List<CourseType> courseTypeTree = getCourseTypeTree(pid);
            //如何数据库中不存在
            if(courseTypeTree==null||courseTypeTree.size()<1){
                //设置当前的值(null)给缓存
                courseTypeTree=new ArrayList<>();
            }
            //如果存在,设置查询出来的数据库的值给缓存
            courseTypeCache.setCourseTypesInCache(courseTypeTree);
            System.out.println("缓存");
            //返回数据库的数据
            return courseTypeTree;
        }else{
            System.out.println("数据库");
            //缓存存在直接返回
            return courseTypesInCache;
        }
    }

    private List<CourseType> getCourseTypeTree(Long pid) {
        List<CourseType> result = new ArrayList<>();
        //查询到所有的类型
        List<CourseType> courseTypeList = courseTypeMapper.selectList(null);
        //遍历所有的类型,拿到每个id,并对应一个自己的类型
        //1.准备一个map
        Map<Long,CourseType> map = new HashMap<>();
        //2.遍历所有类型
        for (CourseType courseType : courseTypeList) {
            //拿到每个id,并对应一个自己的类型
            map.put(courseType.getId(),courseType);
        }
        //再次遍历所有类型,手动完成父亲和子子孙孙的对应关系
        for (CourseType courseType : courseTypeList) {
            //拿到pid,并做与传入的pid做判断,是否当前类型为父亲
            //1.拿到pid
            Long parentId = courseType.getPid();
            System.out.println(parentId);
            //判断当前类型是否为父亲
            if(parentId.longValue()==pid.longValue()){
                //是父亲,直接添加到返回结果的list集合中
                result.add(courseType);
            }else{
                //不是父亲,先拿到当前parentId对应的父亲
                CourseType parent = map.get(parentId);
                System.out.println(parent.getChildren());
                //拿到儿子的集合对象,将当前儿子设置到对应的父亲中
                parent.getChildren().add(courseType);
            }
        }
        return result;
    }

    @Override
    public boolean insert(CourseType entity) {
        //向数据库添加数据
        courseTypeMapper.insert(entity);
        //查询所有
        List<CourseType> courseTypes = queryAllTree(0L);
        //向缓存添加数据
        courseTypeCache.setCourseTypesInCache(courseTypes);
        return true;
    }

    @Override
    public boolean deleteById(Serializable id) {
        //删除数据库数据
        courseTypeMapper.deleteById(id);
        //查询所有
        List<CourseType> courseTypes = queryAllTree(0L);
        //向缓存添加数据
        courseTypeCache.setCourseTypesInCache(courseTypes);
        return true;
    }

    @Override
    public boolean updateById(CourseType entity) {
        //向数据修改数据
        courseTypeMapper.updateById(entity);
        //查询所有
        List<CourseType> courseTypes = queryAllTree(0L);
        //向缓存添加数据
        courseTypeCache.setCourseTypesInCache(courseTypes);
        return true;
    }
}

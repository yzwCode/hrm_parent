package com.yzw.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.yzw.client.ESCourseClient;
import com.yzw.domain.Course;
import com.yzw.domain.ESCourse;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yzw.mapper.CourseDetailMapper;
import com.yzw.mapper.CourseMapper;
import com.yzw.query.CourseQuery;
import com.yzw.service.ICourseService;
import com.yzw.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzw
 * @since 2019-09-03
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseDetailMapper courseDetailMapper;
    @Autowired
    private ESCourseClient esCourseClient;
    @Override
    public PageList<Course> selectListPage(CourseQuery query) {
        Page<Course> page = new Page<>(query.getPage(),query.getRows());
        //System.out.println(query.getPage());
        //System.out.println(query.getRows());
        List<Course> rows =  courseMapper.selectListPage(page,query);
        return new PageList<>(page.getTotal(),rows);
    }

    /**
     * 上线
     * @param ids
     */
    @Override
    public void onLine(Long[] ids) {
        //索引库批量添加数据
        //通过ids查询到数据从数据库中
        List<Course> courses = courseMapper.selectBatchIds(Arrays.asList(ids));
        //将得到的courses转换为ESCourse
        List<ESCourse> esCourseList = courseList2EsCourse(courses);
        esCourseClient.batchSave(esCourseList);
        //批量修改状态
        courseMapper.batchOnline(Arrays.asList(ids));
    }

    /**
     * 下线
     * @param ids
     */
    @Override
    public void offLine(Long[] ids) {
        //索引库批量删除数据
        //通过ids查询到数据从数据库中
        List<Course> courses = courseMapper.selectBatchIds(Arrays.asList(ids));
        //将得到的courses转换为ESCourse
        List<ESCourse> esCourseList = courseList2EsCourse(courses);
        esCourseClient.batchDel(esCourseList);
        //批量修改状态
        courseMapper.batchOffline(Arrays.asList(ids));
    }

    /**
     * 将Course转换为ESCourse
     * @param courses
     * @return
     */
    private List<ESCourse> courseList2EsCourse(List<Course> courses) {
        ArrayList<ESCourse> esCourses = new ArrayList<>();
        for (Course cours : courses) {
            esCourses.add(course2EsCourse(cours));
        }
        return esCourses;
    }

    /**
     * 将Course中的字段封装到映射文档doc中
     * @param course
     * @return
     */
    private ESCourse course2EsCourse(Course course) {
        ESCourse  result = new ESCourse();
        result.setId(course.getId());
        result.setName(course.getName());
        result.setUsers(course.getUsers());
        result.setCourseTypeId(course.getCourseTypeId());
        //type-同库
        if (course.getCourseType() != null)
            result.setCourseTypeName(course.getCourseType().getName());
        //跨服务操作
        result.setGradeId(course.getGrade());
        result.setGradeName(null);
        result.setStatus(course.getStatus());
        result.setTenantId(course.getTenantId());
        result.setTenantName(course.getTenantName());
        result.setUserId(course.getUserId());
        result.setUserName(course.getUserName());
        result.setStartTime(course.getStartTime());
        result.setEndTime(course.getEndTime());
        //Detail
        result.setIntro(null);
        //resource
        result.setResources(null);
        //market
        result.setExpires(null);
        result.setPrice(null);
        result.setPriceOld(null);
        result.setQq(null);
        return result;
    }

    /**
     * 保存课程详情
     */
    @Override
    public boolean insert(Course course) {
        //System.out.println(course);
        course.setStatus(0);
        courseMapper.insert(course);
        //设置课程id给课程详情的id
        course.getDetail().setCourseId(course.getId());
        //System.out.println(course.getId()+"---------------------");
        courseDetailMapper.insert(course.getDetail());
        return true;
    }

    /**
     * 删除课程（数据库）的同时删除对应的索引库的数据
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Serializable id) {
        //删除数据库
        courseMapper.deleteById(id);
        //判断状态-还要删除索引库
        Course course = courseMapper.selectById(id);
        if (  course.getStatus() ==1)
            esCourseClient.delete(Integer.valueOf(id.toString()));
        return true;

    }

    /**
     * 修改课程（数据库）的同时修改对应的索引库的数据
     * @param entity
     * @return
     */
    @Override
    public boolean updateById(Course entity) {
        //修改数据库
        courseMapper.updateById(entity);
        //判断状态-还要修改索引库
        Course course = courseMapper.selectById(entity.getId());
        if ( course.getStatus() ==1)
            esCourseClient.save(course2EsCourse(entity));
        return true;
    }
}

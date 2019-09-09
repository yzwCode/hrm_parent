package com.yzw.service;

import com.baomidou.mybatisplus.service.IService;
import com.yzw.domain.CourseType;
import com.yzw.query.CourseTypeQuery;
import com.yzw.util.PageList;

import java.util.List;

/**
 * <p>
 * 课程目录 服务类
 * </p>
 *
 * @author yzw
 * @since 2019-08-31
 */
public interface ICourseTypeService extends IService<CourseType> {

    PageList<CourseType> selectListPage(CourseTypeQuery query);

    List<CourseType> queryAllTree(Long pid);
}

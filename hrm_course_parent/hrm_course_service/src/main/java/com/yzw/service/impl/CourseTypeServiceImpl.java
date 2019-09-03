package com.yzw.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.yzw.domain.CourseType;
import com.yzw.mapper.CourseTypeMapper;
import com.yzw.query.CourseTypeQuery;
import com.yzw.service.ICourseTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yzw.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public PageList<CourseType> selectListPage(CourseTypeQuery query) {
        Page page = new Page(query.getPage(),query.getRows());
        List<CourseType> courseTypes = courseTypeMapper.selectListPage(page, query);
        return new PageList<>(page.getTotal(),courseTypes);
    }
}

package com.yzw.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.yzw.domain.CourseType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yzw.query.CourseTypeQuery;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程目录 Mapper 接口
 * </p>
 *
 * @author yzw
 * @since 2019-08-31
 */
public interface CourseTypeMapper extends BaseMapper<CourseType> {
    List<CourseType> selectListPage(Page Page, @Param("query") CourseTypeQuery query);
}

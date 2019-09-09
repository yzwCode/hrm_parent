package com.yzw.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yzw.domain.Course;
import com.yzw.query.CourseQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yzw
 * @since 2019-09-03
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> selectListPage(Page page, @Param("query") CourseQuery query);

    /**
     * 批量上线
     * @param asList
     */
    void batchOnline(List<Long> asList);
    /**
     * 批量下线
     * @param asList
     */
    void batchOffline(List<Long> asList);


}

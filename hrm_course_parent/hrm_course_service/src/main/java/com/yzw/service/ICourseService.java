package com.yzw.service;

import com.baomidou.mybatisplus.service.IService;
import com.yzw.domain.Course;
import com.yzw.query.CourseQuery;
import com.yzw.util.PageList;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzw
 * @since 2019-09-03
 */
public interface ICourseService extends IService<Course> {
    //高级查询+分页
    PageList<Course> selectListPage(CourseQuery query);

    /**
     * 上线
     * @param ids
     */
    void onLine(Long[] ids);

    /**
     * 下线
     * @param ids
     */

    void offLine(Long[] ids);
}

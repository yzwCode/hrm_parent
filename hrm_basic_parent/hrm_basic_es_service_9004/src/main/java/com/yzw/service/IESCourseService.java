package com.yzw.service;


import com.yzw.domain.ESCourse;
import com.yzw.query.ESCourseQuery;
import com.yzw.util.PageList;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yzw
 * @since 2019-09-05
 */
public interface IESCourseService {

    PageList<ESCourse> selectListPage(ESCourseQuery query);

    List<ESCourse> selectList(Object o);

    ESCourse selectById(Long id);

    void deleteById(Long id);

    void insert(ESCourse course);

    void updateById(ESCourse course);

    void batchSave(List<ESCourse> esCourseList);

    void batchDel(List<ESCourse> esCourseList);
}

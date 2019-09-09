package com.yzw.client;

import com.yzw.domain.ESCourse;
import com.yzw.query.ESCourseQuery;
import com.yzw.util.AjaxResult;
import com.yzw.util.PageList;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author yaohuaipeng
 * @date 2018/10/8-16:18
 */
@Component
public class ESCourseClientHystrixFallbackFactory implements FallbackFactory<ESCourseClient> {

    @Override
    public ESCourseClient create(Throwable cause) {
        return new ESCourseClient() {
            @Override
            public AjaxResult save(ESCourse course) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public ESCourse get(Long id) {
                return null;
            }

            @Override
            public List<ESCourse> list() {
                return null;
            }

            @Override
            public PageList<ESCourse> json(ESCourseQuery query) {
                return null;
            }

            @Override
            public void batchSave(List<ESCourse> esCourseList) {

            }

            @Override
            public void batchDel(List<ESCourse> esCourseList) {

            }
        };
    }
}

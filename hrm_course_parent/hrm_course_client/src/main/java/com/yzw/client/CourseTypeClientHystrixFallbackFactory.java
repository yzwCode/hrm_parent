package com.yzw.client;

import com.yzw.domain.CourseType;
import com.yzw.query.CourseTypeQuery;
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
public class CourseTypeClientHystrixFallbackFactory implements FallbackFactory<CourseTypeClient> {

    @Override
    public CourseTypeClient create(Throwable throwable) {
        return new CourseTypeClient() {
            @Override
            public AjaxResult save(CourseType courseType) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public CourseType get(Long id) {
                return null;
            }

            @Override
            public List<CourseType> list() {
                return null;
            }

            @Override
            public PageList<CourseType> json(CourseTypeQuery query) {
                return null;
            }
        };
    }
}

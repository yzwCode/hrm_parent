package com.yzw.client;

import com.yzw.domain.Department;
import com.yzw.query.DepartmentQuery;
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
public class DepartmentClientHystrixFallbackFactory implements FallbackFactory<DepartmentClient> {

    @Override
    public DepartmentClient create(Throwable throwable) {
        return new DepartmentClient() {
            @Override
            public AjaxResult save(Department department) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Department get(Long id) {
                return null;
            }

            @Override
            public List<Department> list() {
                return null;
            }

            @Override
            public PageList<Department> json(DepartmentQuery query) {
                return null;
            }
        };
    }
}

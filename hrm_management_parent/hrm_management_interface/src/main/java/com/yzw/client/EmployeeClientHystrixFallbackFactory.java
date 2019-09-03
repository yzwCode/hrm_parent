package com.yzw.client;

import com.yzw.domain.Employee;
import com.yzw.query.EmployeeQuery;
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
public class EmployeeClientHystrixFallbackFactory implements FallbackFactory<EmployeeClient> {

    @Override
    public EmployeeClient create(Throwable throwable) {
        return new EmployeeClient() {
            @Override
            public AjaxResult save(Employee employee) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Employee get(Long id) {
                return null;
            }

            @Override
            public List<Employee> list() {
                return null;
            }

            @Override
            public PageList<Employee> json(EmployeeQuery query) {
                return null;
            }
        };
    }
}

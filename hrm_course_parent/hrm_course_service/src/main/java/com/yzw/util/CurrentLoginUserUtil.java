package com.yzw.util;

import com.yzw.domain.Employee;
import com.yzw.domain.Tenant;

public class CurrentLoginUserUtil {
    public static Tenant getCurrentTranent(){
        Tenant tenant = new Tenant();
        tenant.setId(31L);
        tenant.setCompanyName("中铁二局");
        return tenant;
    }
    public static Employee getCurrentLoginUser(){
        Employee employee = new Employee();
        employee.setId(47L);
        employee.setUsername("张三");
        return employee;
    }
}

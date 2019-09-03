package com.yzw.service.impl;

import com.yzw.domain.Employee;
import com.yzw.domain.Tenant;
import com.yzw.mapper.EmployeeMapper;
import com.yzw.mapper.TenantMapper;
import com.yzw.service.ITenantService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzw
 * @since 2019-09-02
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements ITenantService {
    @Autowired
    private TenantMapper tenantMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public boolean insert(Tenant tenant) {
        //添加机构,返回id
        tenant.setRegisterTime(new Date());
        tenant.setState(true);

        tenantMapper.insert(tenant);
        /**
         * 添加管理员
         */
        //拿到当前租户设置的管理员
        Employee adminUser = tenant.getAdminUser();
        adminUser.setInputTime(new Date());
        adminUser.setState(0);
        //设置为租户管理员
        adminUser.setType(true);
        //给管理员设置租户的id
        adminUser.setTenantId(tenant.getId());
        //保存管理员信息
        employeeMapper.insert(adminUser);
        //添加套餐和机构中间表
        tenantMapper.saveTenantMeals(tenant.getMealsMap());
        return true;
    }

    @Override
    public boolean deleteById(Serializable id) {
        return super.deleteById(id);
    }

    @Override
    public boolean updateById(Tenant entity) {
        return super.updateById(entity);
    }
}

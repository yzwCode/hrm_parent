package com.yzw.service.impl;

import com.yzw.domain.Employee;
import com.yzw.mapper.EmployeeMapper;
import com.yzw.service.IEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzw
 * @since 2019-09-02
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}

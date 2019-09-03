package com.yzw.service.impl;

import com.yzw.domain.Department;
import com.yzw.mapper.DepartmentMapper;
import com.yzw.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}

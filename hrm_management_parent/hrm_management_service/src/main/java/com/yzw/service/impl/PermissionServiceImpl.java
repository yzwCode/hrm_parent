package com.yzw.service.impl;

import com.yzw.domain.Permission;
import com.yzw.mapper.PermissionMapper;
import com.yzw.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}

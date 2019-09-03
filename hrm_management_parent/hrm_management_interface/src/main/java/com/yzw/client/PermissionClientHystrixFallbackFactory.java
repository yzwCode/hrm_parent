package com.yzw.client;

import com.yzw.domain.Permission;
import com.yzw.query.PermissionQuery;
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
public class PermissionClientHystrixFallbackFactory implements FallbackFactory<PermissionClient> {

    @Override
    public PermissionClient create(Throwable throwable) {
        return new PermissionClient() {
            @Override
            public AjaxResult save(Permission permission) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Permission get(Long id) {
                return null;
            }

            @Override
            public List<Permission> list() {
                return null;
            }

            @Override
            public PageList<Permission> json(PermissionQuery query) {
                return null;
            }
        };
    }
}

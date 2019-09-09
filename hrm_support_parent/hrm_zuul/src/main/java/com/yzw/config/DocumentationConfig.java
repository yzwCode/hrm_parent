package com.yzw.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("系统管理", "/services/myManage/v2/api-docs", "1.0"));
        resources.add(swaggerResource("课程中心", "/services/myCourse/v2/api-docs", "1.0"));
        resources.add(swaggerResource("分布式文件管理系统", "/services/myFastDfs/v2/api-docs", "1.0"));
        resources.add(swaggerResource("分布式全文检索服务管理系统", "/services/myEs/v2/api-docs", "1.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
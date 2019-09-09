package com.yzw;

import com.yzw.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class FastDfsClientHystrixFallbackFactory implements FallbackFactory<FastDfsClient> {
    @Override
    public FastDfsClient create(Throwable cause) {
        return new FastDfsClient() {
            @Override
            public String upload(MultipartFile multipartFile) {
                return null;
            }

            @Override
            public AjaxResult delete(String path) {
                return null;
            }

            @Override
            public void downLoad(String path) {

            }
        };
    }
}

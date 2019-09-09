package com.yzw;

import com.yzw.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(value = "HRM-ZUUL",configuration = FeignClientsConfiguration.class,
        fallbackFactory = FastDfsClientHystrixFallbackFactory.class)
@RequestMapping("/fastDfs")
public interface FastDfsClient {
    @PostMapping(value = "/upload")
    String upload(@RequestBody MultipartFile multipartFile);
    /**
     * 删除文件
     */
    @DeleteMapping("/delete")
    AjaxResult delete(@RequestParam("path") String path);
    /**
     * 下载文件
     */
    @GetMapping("/downLoad")
    void downLoad(@RequestParam("path") String path);
}
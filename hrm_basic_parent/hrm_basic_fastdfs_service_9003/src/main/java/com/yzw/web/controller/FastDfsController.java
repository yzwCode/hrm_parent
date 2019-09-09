package com.yzw.web.controller;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.yzw.util.AjaxResult;
import com.yzw.util.FastDfsApiOpr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/fastDfs")
public class FastDfsController {
    Logger logger = LoggerFactory.getLogger(FastDfsController.class);
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            //拿到原文件的名字
            String filename = file.getOriginalFilename();
            System.out.println("fileName"+filename);
            //拿到文件后缀
            String suffix = filename.substring(filename.lastIndexOf(".") + 1);
            return FastDfsApiOpr.upload(file.getBytes(),suffix);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("error"+e.getMessage());
        }
        return null;
    }
    @DeleteMapping("/delete")
    public AjaxResult delete(@RequestParam("path") String path) {
        try {
            System.out.println("path"+path);
            String pathTemp = path.substring(1);
            String groupName  = pathTemp.substring(0, pathTemp.indexOf("/"));
            System.out.println(groupName);
            String remotePath  = pathTemp.substring(pathTemp.indexOf("/") + 1);
            System.out.println(remotePath);
            //代用工具类,传入组名和远程路径
            FastDfsApiOpr.delete(groupName,remotePath);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("delete fail!"+e.getMessage());
            return AjaxResult.me().setSuccess(false).setMessage(e.getMessage());
        }
    }
    @GetMapping("/downLoad")
    public void downLoad(@RequestParam("path")String path, HttpServletResponse response) {
        String pathTemp = path.substring(1);
        String groupName  = pathTemp.substring(0, pathTemp.indexOf("/"));
        String remotePath  = pathTemp.substring(pathTemp.indexOf("/") + 1);
        byte[] bytes = FastDfsApiOpr.download(groupName, remotePath);
        ServletOutputStream outputStream = null;
        ByteInputStream byteInputStream = null;
        //直接通过流的方式返回
        try {
            outputStream = response.getOutputStream();
            byteInputStream = new ByteInputStream(bytes,bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("downLoad fail!"+e.getMessage());
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (byteInputStream != null) {
                try {
                    byteInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

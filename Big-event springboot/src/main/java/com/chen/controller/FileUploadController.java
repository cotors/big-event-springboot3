package com.chen.controller;

import com.chen.pojo.Result;
import com.chen.utils.AliOssUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: Cotors
 * @create 2024-03-16-20:08
 */
@RestController
public class FileUploadController {

    //LTAI5tGDgCxgfGSWaK342Lk6
    //3VvRQ9xIW79mvrEUbFgd6DSIpg1wTp

    @PostMapping("/upload")
    public Result<String> file(MultipartFile file) throws Exception {
        //获取图片原始名(名字+后缀)
        String originalFilename = file.getOriginalFilename();
        //UUID.randomUUID().toString():随机获取一串数字
        //originalFilename.substring():从指定的位置起，截取到末尾的字符串

        String n_file = UUID.randomUUID().toString() +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        //方法一:保存到本地磁盘
//        file.transferTo(new File("C:\\Users\\chen\\Desktop\\springboot3+vue3大事件项目\\images\\" + n_file));

        //方法二:保存到阿里云()对象存储oss
        String url = AliOssUtils.uploadFile(n_file,file.getInputStream());

        return Result.success(url);
    }

}

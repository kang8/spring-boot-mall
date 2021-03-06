package com.kang.mall.controller;

import com.kang.mall.common.Constants;
import com.kang.mall.common.Result;
import com.kang.mall.config.properties.MallUploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

/**
 * @author yikang
 * ClassName: UploadController
 * Description: 文件上传
 * Create Date: 2021/1/25 21:34
 */
@RestController
public class UploadController {
    @Autowired
    private MallUploadProperties upload;

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("请传入上传文件");
        }
        String filename = file.getOriginalFilename();
        String suffixName = Objects.requireNonNull(filename).substring(filename.lastIndexOf("."));
        String prefixName = Objects.requireNonNull(filename).substring(0, filename.lastIndexOf("."));

        // generate new filename
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        String newFileName = prefixName + '-' + sdf.format(new Date()) + r.nextInt(100) + suffixName;

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(upload.getDirectory() + newFileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败");
        }

        newFileName = Constants.PATH_FOR_ACCESS_UPLOAD_FILE + "/" + newFileName;

        return Result.ok("上传成功", newFileName);
    }
}

package com.kang.mall.controller;

import com.kang.mall.common.Result;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${mall.upload.directory}")
    private String fileUploadPath;

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
        StringBuilder tempName = new StringBuilder();
        tempName.append(prefixName).append('-').append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(fileUploadPath + newFileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败");
        }

        return Result.ok("上传成功", newFileName);
    }
}

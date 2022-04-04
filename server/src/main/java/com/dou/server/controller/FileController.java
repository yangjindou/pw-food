package com.dou.server.controller;

import com.dou.server.model.SysFile;
import com.dou.server.service.FileService;
import com.dou.server.tag.PassToken;
import com.dou.server.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RequestMapping("/file")
@RequiredArgsConstructor
@RestController
@Api(tags = "文件相关接口")
public class FileController {

    private final FileService fileService;

    @Value("${application.file.upload.path}")
    private String uploadPath;

    @PassToken
    @ApiOperation(value = "上传文件", notes = "返回文件信息")
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        String fileNewName = UUID.randomUUID().toString();
        File newFile = FileUtils.upload(file, uploadPath, fileNewName);
        SysFile sysFile = new SysFile().setName(newFile.getName()).setRealName(file.getOriginalFilename()).setPath(newFile.getPath());
        fileService.add(sysFile);
        return ResponseEntity.ok(sysFile);
    }
}
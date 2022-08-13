package com.kuang.service.oss.controller.admin;

import com.kuang.common.base.result.R;
import com.kuang.common.base.result.ResultCodeEnum;
import com.kuang.common.base.util.ExceptionUtils;
import com.kuang.service.base.exception.MyException;
import com.kuang.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.MulticastChannel;

@Api(description = "阿里云文件管理")
//@CrossOrigin
@RestController
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;


    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R upload(
            @ApiParam(value = "文件",required = true) @RequestParam("file") MultipartFile file,
            @ApiParam(value = "模块",required = true) @RequestParam("module") String module) throws IOException {
        try{
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String url = fileService.upload(inputStream, module, originalFilename);

            return R.ok().message("文件上传成功").data("url",url);
        }catch (Exception e){
            log.error(ExceptionUtils.getMessage(e));
            throw new MyException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    @ApiOperation(value = "文件删除")
    @DeleteMapping("/remove")
    public R removeFile(@ApiParam(value = "要删除的文件路径",required = true)
                        @RequestBody String url){
        fileService.remove(url);
        return R.ok().message("文件删除成功");
    }
    @ApiOperation(value = "测试")
    @GetMapping("/test")
    public R test(){
        log.info("oss test被调用");
        return R.ok();
    }
}

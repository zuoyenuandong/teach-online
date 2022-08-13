package com.kuang.service.vod.controllr.admin;


import com.kuang.common.base.result.R;
import com.kuang.common.base.result.ResultCodeEnum;
import com.kuang.common.base.util.ExceptionUtils;
import com.kuang.service.base.exception.MyException;
import com.kuang.service.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping("/admin/vod/video")
@Api(description = "阿里云视频点播")
@Slf4j
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public R uploadVideo(@ApiParam(name = "file",value = "文件",required = true)
                         @RequestParam("file")MultipartFile file){
        System.out.println(file.getOriginalFilename());
        try {
            InputStream inputStream = file.getInputStream();
            String videoId = videoService.uploadVideo(inputStream, file.getOriginalFilename());
            return R.ok().message("视频上传成功").data("videoId",videoId);
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new MyException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }
    }

    @DeleteMapping("remove/{vodId}")
    public R removeVideo(
            @ApiParam(value="阿里云视频id", required = true)
            @PathVariable String vodId){

        try {
            videoService.removeVideo(vodId);
            return R.ok().message("视频删除成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new MyException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }

    @DeleteMapping("remove")
    public R removeVideoByIdList(
            @ApiParam(value = "阿里云视频id列表", required = true)
            @RequestBody List<String> videoIdList){

        try {
            videoService.removeVideoByIdList(videoIdList);
            return  R.ok().message("视频删除成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new MyException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }



}

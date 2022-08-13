package com.kuang.service.edu.feign;

import com.kuang.common.base.result.R;
import com.kuang.service.edu.feign.fallback.OssFileServiceFallback;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@FeignClient(value = "service-vod",fallback = OssFileServiceFallback.class)
public interface VodVideoService {
    @PostMapping("/admin/vod/video/upload")
    R uploadVideo(@RequestParam("file") MultipartFile file);

    @DeleteMapping("/admin/vod/video/remove/{vodId}")
    R removeVideo(@PathVariable("vodId") String vodId);

    @DeleteMapping("/admin/vod/video/remove")
    R removeVideoByIdList(@RequestBody List<String> videoIdList);
}

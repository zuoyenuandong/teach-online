package com.kuang.service.edu.feign;

import com.kuang.common.base.result.R;
import com.kuang.service.edu.feign.fallback.OssFileServiceFallback;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "service-oss",fallback = OssFileServiceFallback.class)
public interface OssFileService {
    @GetMapping("/admin/oss/file/test")
    R test();


    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(@RequestBody String url);
}

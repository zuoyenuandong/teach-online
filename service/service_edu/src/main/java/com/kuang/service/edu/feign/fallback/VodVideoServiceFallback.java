package com.kuang.service.edu.feign.fallback;

import com.kuang.common.base.result.R;
import com.kuang.service.edu.feign.VodVideoService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class VodVideoServiceFallback implements VodVideoService {
    @Override
    public R uploadVideo(MultipartFile file) {
        return R.error();
    }

    @Override
    public R removeVideo(String vodId) {
        return R.error();
    }

    @Override
    public R removeVideoByIdList(List<String> videoIdList) {
        return R.error();
    }
}

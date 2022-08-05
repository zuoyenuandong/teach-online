package com.kuang.service.edu.feign.fallback;

import com.kuang.common.base.result.R;
import com.kuang.service.edu.feign.OssFileService;
import org.springframework.stereotype.Service;

@Service
public class OssFileServiceFallback implements OssFileService {
    @Override
    public R test() {
        return R.error();
    }

    @Override
    public R removeFile(String url) {
        return R.error();
    }
}

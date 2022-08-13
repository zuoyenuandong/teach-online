package com.kuang.service.trade.feign.fallback;

import com.kuang.common.base.result.R;
import com.kuang.service.base.dto.CourseDto;
import com.kuang.service.trade.feign.EduCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EduCourseServiceFallBack implements EduCourseService {
    @Override
    public CourseDto getCourseDtoById(String courseId) {
        log.info("熔断保护");
        return null;
    }
    @Override
    public R updateBuyCountById(String id) {
        log.error("熔断器被执行");
        return R.error();
    }
}

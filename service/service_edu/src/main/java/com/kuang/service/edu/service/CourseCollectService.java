package com.kuang.service.edu.service;

import com.kuang.service.edu.entity.CourseCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuang.service.edu.entity.ov.CourseCollectVo;

import java.util.List;

/**
 * <p>
 * 课程收藏 服务类
 * </p>
 *
 * @author Kuang
 * @since 2022-08-03
 */
public interface CourseCollectService extends IService<CourseCollect> {

    boolean isCollect(String courseId, String id);

    void saveCourseCollect(String courseId, String id);

    List<CourseCollectVo> selectListByMemberId(String id);

    boolean removeCourseCollect(String courseId, String id);
}

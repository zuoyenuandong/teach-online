package com.kuang.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kuang.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuang.service.edu.entity.form.CourseInfoForm;
import com.kuang.service.edu.entity.ov.CoursePublishVo;
import com.kuang.service.edu.entity.ov.CourseQueryVo;
import com.kuang.service.edu.entity.ov.CourseVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Kuang
 * @since 2022-08-03
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    String updateCourseInfo(CourseInfoForm courseInfoForm);

    IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo);


    Boolean removeCoverById(String id);

    boolean removeCourseById(String id);

    CoursePublishVo getCoursePublishById(String id);

    boolean publishCourseById(String id);
}

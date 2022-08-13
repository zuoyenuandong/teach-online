package com.kuang.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kuang.service.base.dto.CourseDto;
import com.kuang.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuang.service.edu.entity.form.CourseInfoForm;
import com.kuang.service.edu.entity.ov.*;

import java.util.List;

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

    List<Course> webSelectList(WebCourseQueryVo webCourseQueryVo);

    WebCourseVo selectWebCourseVoById(String id);

    List<Course> selectHotCourse();

    CourseDto getCourseDtoById(String courseId);

    void updateBuyCountById(String id);
}

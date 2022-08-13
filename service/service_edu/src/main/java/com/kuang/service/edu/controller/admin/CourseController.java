package com.kuang.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.common.base.result.R;
import com.kuang.service.edu.entity.Course;
import com.kuang.service.edu.entity.form.CourseInfoForm;
import com.kuang.service.edu.entity.ov.CoursePublishVo;
import com.kuang.service.edu.entity.ov.CourseQueryVo;
import com.kuang.service.edu.entity.ov.CourseVo;
import com.kuang.service.edu.service.CourseDescriptionService;
import com.kuang.service.edu.service.CourseService;
import com.kuang.service.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Kuang
 * @since 2022-08-03
 */

@Api(description = "课程管理")
@RestController
@RequestMapping("/admin/edu/course")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private VideoService videoService;



    @ApiOperation("新增课程")
    @PostMapping("/save-course-info")
    public R saveCourseInfo(
            @ApiParam(value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm
    ) {
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return R.ok().data("courseId", courseId).message("保存成功");
    }

    @ApiOperation("根据id查询课程")
    @GetMapping("/course-info/{id}")
    public R getById(
            @ApiParam(value = "课程id", required = true)
            @PathVariable("id") String id
    ) {

        CourseInfoForm courseInfoForm = courseService.getCourseInfoById(id);
        if (courseInfoForm != null){
            return R.ok().data("courseInfo", courseInfoForm).message("保存成功");
        }else {
            return R.ok().message("数据不存在");
        }

    }

    @ApiOperation("根据id更新课程")
    @PostMapping("/update-course-info")
    public R updateCourseInfo(
            @ApiParam(value = "更新信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm
    ) {
        String courseId = courseService.updateCourseInfo(courseInfoForm);
        return R.ok().data("courseId", courseId).message("更新成功");
    }

    @ApiOperation(value = "课程分页列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(@ApiParam("当前页码") @PathVariable("page") Long page,
                      @ApiParam("每页条数") @PathVariable("limit") Long limit,
                      @ApiParam("讲师列表查询对象") CourseQueryVo courseQueryVo){
        IPage<CourseVo> pageModel = courseService.selectPage(page,limit, courseQueryVo);
        List<CourseVo> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "根据id删除课程")
    @DeleteMapping("/remove/{id}")
    public R removeById(@PathVariable("id") String id){
        //删除课程封面
        courseService.removeCoverById(id);
        //删除课程视频
        videoService.removeVideoByCourseId(id);
        //删除课程
        boolean result = courseService.removeCourseById(id);

        if (result){
            return R.ok().message("删除成功");
        }

        return R.error().message("客户端删除失败");
    }

    @ApiOperation(value = "根据id获取课程发布信息")
    @GetMapping("/course-publish/{id}")
    public R getCoursePublishVoById(
            @ApiParam(value = "课程id",required = true)
            @PathVariable("id") String id){
        CoursePublishVo coursePublishVo = courseService.getCoursePublishById(id);
        if (coursePublishVo!=null){
            return R.ok().data("item",coursePublishVo);
        }else {
            return R.error().message("数据不存在");
        }


    }

    @ApiOperation("根据id发布课程")
    @PutMapping("publish-course/{id}")
    public R publishCourseById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id){

        boolean result = courseService.publishCourseById(id);
        if (result) {
            return R.ok().message("发布成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

}


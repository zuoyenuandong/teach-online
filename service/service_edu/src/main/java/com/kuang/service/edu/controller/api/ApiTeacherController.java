package com.kuang.service.edu.controller.api;

import com.kuang.common.base.result.R;
import com.kuang.service.edu.entity.Teacher;
import com.kuang.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Api(description="讲师")
@RestController
@RequestMapping("/api/edu/teacher")
public class ApiTeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value="所有讲师列表")
    @GetMapping("/list")
    public R listAll(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items", list).message("获取讲师列表成功");
    }

    @ApiOperation(value="所有讲师信息及课程")
    @GetMapping("/get/{id}")
    public R get(
            @ApiParam(value = "讲师id",required = true)
            @PathVariable("id") String id)
    {
        Map<String, Object> map = teacherService.selectTeacherInfoById(id);
        return R.ok().data(map);
    }
}

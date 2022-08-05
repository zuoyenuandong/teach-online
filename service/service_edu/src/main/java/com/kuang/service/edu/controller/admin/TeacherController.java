package com.kuang.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.common.base.result.R;
import com.kuang.service.edu.entity.Teacher;
import com.kuang.service.edu.entity.ov.TeacherQueryVo;
import com.kuang.service.edu.feign.OssFileService;
import com.kuang.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Kuang
 * @since 2022-08-03
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/edu/teacher")
@Api("讲师后台管理系统")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private OssFileService ossFileService;

    @ApiOperation(value = "查询所有的讲师")
    @GetMapping("/list")
    public R listAll(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list);
    }
    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping("/remove/{id}")
    public R removeById(@PathVariable("id") String id){
        teacherService.removeAvatarById(id);
        boolean result =  teacherService.removeById(id);
        if (result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("数据不存在");
        }
    }
    @ApiOperation(value = "讲师分页列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(@ApiParam("当前页码") @PathVariable("page") Long page,
                      @ApiParam("每页条数") @PathVariable("limit") Long limit,
                      @ApiParam("讲师列表查询对象")TeacherQueryVo teacherQueryVo){
        Page<Teacher> pageParam = new Page<>(page,limit);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam, teacherQueryVo);
        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("/save")
    public R save(@ApiParam("讲师对象") @RequestBody Teacher teacher){
        boolean result = teacherService.save(teacher);
        if (result){
            return R.ok().message("添加成功");
        }else {
            return R.error().message("添加不存在");
        }
    }
    @ApiOperation(value = "更新讲师")
    @PutMapping("/update")
    public R updateById(@ApiParam("讲师对象，id必须存在") @RequestBody Teacher teacher){
        boolean result = teacherService.updateById(teacher);
        if (result){
            return R.ok().message("添修改成功");
        }else {
            return R.error().message("修改失败");
        }
    }
    @ApiOperation(value = "根据id获取讲师信息")
    @GetMapping("/get/{id}")
    public R getById(@ApiParam("讲师对象") @PathVariable("id") String id){
        Teacher teacher = teacherService.getById(id);
        if (teacher != null){
            return R.ok().data("item",teacher);
        }else {
            return R.error().message("数据不存在");
        }
    }
    @ApiOperation(value = "根据id列表删除讲师")
    @DeleteMapping("/banch-remove")
    public R removeRows(
            @ApiParam(value = "删除讲师id列表",required = true)
            @RequestBody List<String> idList){
        boolean result =  teacherService.removeByIds(idList);
        if (result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation(value = "测试")
    @GetMapping("/test")
    public R test(){
        ossFileService.test();
        return R.ok();
    }

    @ApiOperation(value = "测试并发")
    @GetMapping("/testCurrent")
    public R testCurrent(){
        return R.ok();
    }


}


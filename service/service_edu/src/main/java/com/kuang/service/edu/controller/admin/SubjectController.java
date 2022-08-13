package com.kuang.service.edu.controller.admin;


import com.kuang.common.base.result.R;
import com.kuang.common.base.result.ResultCodeEnum;
import com.kuang.service.base.exception.MyException;
import com.kuang.service.edu.entity.ov.SubjectVo;
import com.kuang.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Kuang
 * @since 2022-08-03
 */

@RestController
@RequestMapping("/admin/edu/subject")
@Api(description ="课程分类管理")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @ApiOperation("批量文件导入")
    @PostMapping("/import")
    public R batchImport(
            @ApiParam(value = "excel文件",required = true)
            @RequestParam("file") MultipartFile file){
        try {
            subjectService.batchImport(file.getInputStream());
            return R.ok().message("批量导入成功");
        } catch (Exception e) {
            throw new MyException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }
    @ApiOperation("嵌套数据列表")
    @GetMapping("/nested-list")
    public R nestedList(){
        List<SubjectVo>  subjectVoList =  subjectService.nestedList();
        return R.ok().data("items",subjectVoList);
    }

}


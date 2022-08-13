package com.kuang.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuang.service.edu.entity.ov.TeacherQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Kuang
 * @since 2022-08-03
 */
public interface TeacherService extends IService<Teacher> {

    IPage<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVo teacherQueryVo);

    Boolean removeAvatarById(String id);

    Map<String,Object> selectTeacherInfoById(String id);

    List<Teacher> selectHotTeacher();
}

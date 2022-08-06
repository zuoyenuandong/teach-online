package com.kuang.service.edu.service;

import com.kuang.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuang.service.edu.entity.ov.SubjectVo;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Kuang
 * @since 2022-08-03
 */
public interface SubjectService extends IService<Subject> {

    void batchImport(InputStream inputStream);

    List<SubjectVo> nestedList();
}

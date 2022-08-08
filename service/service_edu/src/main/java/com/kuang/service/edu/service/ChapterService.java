package com.kuang.service.edu.service;

import com.kuang.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuang.service.edu.entity.ov.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Kuang
 * @since 2022-08-03
 */
public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);
}

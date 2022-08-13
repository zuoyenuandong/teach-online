package com.kuang.service.edu.service;

import com.kuang.service.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Kuang
 * @since 2022-08-03
 */
public interface VideoService extends IService<Video> {

    void removeVideoById(String id);


    void removeVideoByChapterId(String chapterId);

    void removeVideoByCourseId(String courseId);
}

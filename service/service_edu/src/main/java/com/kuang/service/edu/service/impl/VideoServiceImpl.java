package com.kuang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.service.edu.entity.Video;
import com.kuang.service.edu.feign.VodVideoService;
import com.kuang.service.edu.mapper.VideoMapper;
import com.kuang.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Kuang
 * @since 2022-08-03
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Autowired
    private VodVideoService vodVideoService;
    @Override
    public void removeVideoById(String id) {
        Video video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        vodVideoService.removeVideo(videoSourceId);
    }

    @Override
    public void removeVideoByChapterId(String chapterId) {
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.select("video_source_id");
        videoQueryWrapper.eq("chapter_id",chapterId);

        List<Map<String, Object>> maps = baseMapper.selectMaps(videoQueryWrapper);

        List<String> videoSourceIdList = new ArrayList<>();

        for (Map<String, Object> map : maps) {
            videoSourceIdList.add((String)map.get("video_source_id"));
        }

        vodVideoService.removeVideoByIdList(videoSourceIdList);
    }

    @Override
    public void removeVideoByCourseId(String courseId) {

        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.select("video_source_id");
        videoQueryWrapper.eq("course_id",courseId);
        List<Map<String, Object>> maps = baseMapper.selectMaps(videoQueryWrapper);

        List<String> videoSourceIdList = new ArrayList<>();

        for (Map<String, Object> map : maps) {
            videoSourceIdList.add((String)map.get("video_source_id"));
        }

        vodVideoService.removeVideoByIdList(videoSourceIdList);
    }


}

package com.kuang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.service.edu.entity.Chapter;
import com.kuang.service.edu.entity.Video;
import com.kuang.service.edu.entity.ov.ChapterVo;
import com.kuang.service.edu.entity.ov.VideoVo;
import com.kuang.service.edu.mapper.ChapterMapper;
import com.kuang.service.edu.mapper.VideoMapper;
import com.kuang.service.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Kuang
 * @since 2022-08-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoMapper videoMapper;
    @Override
    public boolean removeChapterById(String id) {
        //删除远程视频

        //删除章节相关联的视频
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id",id);
        videoMapper.delete(videoQueryWrapper);

        //删除章节
        return this.removeById(id);
    }

    @Override
    public List<ChapterVo> nestedList(String courseId) {
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        chapterQueryWrapper.orderByAsc("sort","id");
        List<Chapter> chapterList = baseMapper.selectList(chapterQueryWrapper);

        QueryWrapper<Video> videQueryWrapper = new QueryWrapper<>();
        videQueryWrapper.eq("course_id",courseId);
        videQueryWrapper.orderByAsc("sort","id");
        List<Video> videoList = videoMapper.selectList(videQueryWrapper);

        List<ChapterVo> chapterVoList = new ArrayList<>();

        for (Chapter chapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            List<VideoVo> videoVoList = new ArrayList<>();
            for (Video video : videoList) {
                if (video.getChapterId().equals(chapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
            chapterVoList.add(chapterVo);
        }
        return chapterVoList;
    }
}

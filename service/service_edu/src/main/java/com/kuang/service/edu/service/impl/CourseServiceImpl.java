package com.kuang.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.common.base.result.R;
import com.kuang.service.edu.entity.*;
import com.kuang.service.edu.entity.form.CourseInfoForm;
import com.kuang.service.edu.entity.ov.CoursePublishVo;
import com.kuang.service.edu.entity.ov.CourseQueryVo;
import com.kuang.service.edu.entity.ov.CourseVo;
import com.kuang.service.edu.feign.OssFileService;
import com.kuang.service.edu.mapper.*;
import com.kuang.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CourseCollectMapper courseCollectMapper;
    @Autowired
    private OssFileService ossFileService;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        //将courseInfoForm数据分别传入两张表
        //传入Course表
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        course.setStatus(Course.COURSE_DRAFT);
        baseMapper.insert(course);


        //传入CourseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.insert(courseDescription);

        //返回课程id
        return course.getId();
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CourseInfoForm getCourseInfoById(String id) {
        Course course = baseMapper.selectById(id);
        if (course == null){
            return null;
        }
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course,courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());
        return courseInfoForm;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateCourseInfo(CourseInfoForm courseInfoForm) {
        //将courseInfoForm数据分别传入两张表
        //传入Course表
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        baseMapper.updateById(course);


        //传入CourseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.updateById(courseDescription);

        //返回课程id
        return course.getId();
    }

    @Override
    public IPage<CourseVo> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo) {
        QueryWrapper<CourseVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("c.gmt_create");

        String subjectId = courseQueryVo.getSubjectId();
        String subjectParentId = courseQueryVo.getSubjectParentId();
        String teacherId = courseQueryVo.getTeacherId();
        String title = courseQueryVo.getTitle();

        if (!StringUtils.isEmpty(title)){
            queryWrapper.like("c.title",title);
        }
        if (!StringUtils.isEmpty(teacherId)){
            queryWrapper.eq("c.teacher_id",teacherId);
        }
        if (!StringUtils.isEmpty(subjectId)){
            queryWrapper.eq("c.subject_id",subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.eq("c.subject_parent_id",subjectParentId);
        }


        Page<CourseVo> pageParam = new Page<>(page, limit);
        List<CourseVo> records = baseMapper.selectPageByCourseQueryVo(pageParam,queryWrapper);

        return pageParam.setRecords(records);
    }

    @Override
    public Boolean removeCoverById(String id) {
        Course course = baseMapper.selectById(id);
        if (course!=null){
            String cover = course.getCover();
            if (StringUtils.isEmpty(cover)){
                R r = ossFileService.removeFile(cover);
                return r.getSuccess();
            }
        }
        return false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeCourseById(String id) {
        //删除课程关联的视频
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        videoMapper.delete(videoQueryWrapper);
        //删除课程关联的课程章节
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",id);
        chapterMapper.delete(chapterQueryWrapper);
        //删除课程关联的课程描述
        courseDescriptionMapper.deleteById(id);
        //删除课程关联的评论
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("course_id",id);
        commentMapper.delete(commentQueryWrapper);
        //删除课程关联的收藏信息
        QueryWrapper<CourseCollect> courseCollectQueryWrapper = new QueryWrapper<>();
        courseCollectQueryWrapper.eq("course_id",id);
        courseCollectMapper.delete(courseCollectQueryWrapper);
        //删除课程
        return this.removeById(id);


    }

    @Override
    public CoursePublishVo getCoursePublishById(String id) {
        return baseMapper.selectCoursePublishById(id);

    }

    @Override
    public boolean publishCourseById(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_NORMAL);
        return this.updateById(course);
    }


}

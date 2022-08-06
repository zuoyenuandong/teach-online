package com.kuang.service.edu.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kuang.service.edu.entity.Subject;
import com.kuang.service.edu.entity.excel.ExcelSubjectData;
import com.kuang.service.edu.mapper.SubjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData> {

    private SubjectMapper subjectMapper;


    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        String levelOneTitle = excelSubjectData.getLevelOneTitle();
        String levelTwoTitle = excelSubjectData.getLevelTwoTitle();
        String parentId = null;
        //判断一级列表是否存在
        Subject subjectLevelOne = getByTitle(levelOneTitle);
        if(subjectLevelOne==null){
            Subject subject = new Subject();
            subject.setParentId("0");
            subject.setTitle(levelOneTitle);
            subjectMapper.insert(subject);
            parentId = subject.getId();
        }else {
            parentId = subjectLevelOne.getId();
        }
        Subject subjectLevelTwo = getByTitleAndParentId(levelTwoTitle,parentId);
        if (subjectLevelTwo==null){
            Subject subjectChild = new Subject();
            subjectChild.setParentId(parentId);
            subjectChild.setTitle(levelTwoTitle);
            subjectMapper.insert(subjectChild);
        }


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    private Subject getByTitle(String title){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        queryWrapper.eq("parent_id",0);
        return subjectMapper.selectOne(queryWrapper);
    }
    private Subject getByTitleAndParentId(String title,String parentId){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        queryWrapper.eq("parent_id",parentId);
        return subjectMapper.selectOne(queryWrapper);
    }
}

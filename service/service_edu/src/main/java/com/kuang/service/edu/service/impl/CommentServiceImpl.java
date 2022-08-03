package com.kuang.service.edu.service.impl;

import com.kuang.service.edu.entity.Comment;
import com.kuang.service.edu.mapper.CommentMapper;
import com.kuang.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Kuang
 * @since 2022-08-03
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}

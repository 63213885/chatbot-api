package com.chatbot.ai.domain.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatbot.ai.domain.comment.model.entity.Comment;
import com.chatbot.ai.domain.comment.service.CommentService;
import com.chatbot.ai.domain.mapper.CommentMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public boolean isReplied(Long commentId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("commentId", commentId);
        Comment comment = this.baseMapper.selectOne(queryWrapper);
        return comment != null;
    }
}

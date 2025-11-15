package com.chatbot.ai.domain.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chatbot.ai.domain.comment.model.entity.Comment;

public interface CommentService extends IService<Comment> {

    boolean isReplied(Long commentId);
}

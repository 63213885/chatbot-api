package com.chatbot.ai.domain.comment.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@TableName(value = "comment")
@Data
@AllArgsConstructor
public class Comment implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long commentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}

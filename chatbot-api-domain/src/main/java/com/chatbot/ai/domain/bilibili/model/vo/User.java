package com.chatbot.ai.domain.bilibili.model.vo;

import lombok.Data;

@Data
public class User {

    private Long mid;

    private Long fans;

    private String nickname;

    private String avatar;

    private String mid_link;

    private Boolean follow;
}

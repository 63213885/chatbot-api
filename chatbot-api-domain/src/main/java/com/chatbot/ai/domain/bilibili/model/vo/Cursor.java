package com.chatbot.ai.domain.bilibili.model.vo;

import lombok.Data;

@Data
public class Cursor {

    private Boolean is_end;

    private Long id;

    private Long time;
}

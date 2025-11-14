package com.chatbot.ai.domain.bilibili.model.vo;

import lombok.Data;

@Data
public class Items {

    private Long id;

    private User user;

    private Item item;

    private Long at_time;
}

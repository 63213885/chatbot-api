package com.chatbot.ai.domain.bilibili.model.vo;

import lombok.Data;

@Data
public class Items {

    private Integer id;

    private User user;

    private Item item;

    private Integer at_time;
}

package com.chatbot.ai.domain.bilibili.model.aggregates;


import com.chatbot.ai.domain.bilibili.model.vo.Data;

@lombok.Data
public class UnAnsweredQuestionsAggregates {

    private Integer code;

    private String message;

    private Data data;
}

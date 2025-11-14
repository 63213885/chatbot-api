package com.chatbot.ai.domain.bilibili.model.aggregates;


import com.chatbot.ai.domain.bilibili.model.response.Data;

@lombok.Data
public class UnAnsweredQuestionsAggregates {

    private Long code;

    private String message;

    private Long ttl;

    private Data data;
}

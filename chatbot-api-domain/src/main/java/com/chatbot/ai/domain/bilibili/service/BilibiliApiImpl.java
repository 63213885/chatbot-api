package com.chatbot.ai.domain.bilibili.service;

import com.chatbot.ai.domain.bilibili.BilibiliApi;
import com.chatbot.ai.domain.bilibili.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

public class BilibiliApiImpl implements BilibiliApi {
    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestions(String cookie) throws IOException {
        return null;
    }

    @Override
    public boolean answer(String cookie) throws IOException {
        return false;
    }
}

package com.chatbot.ai.domain.bilibili;

import com.chatbot.ai.domain.bilibili.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 * Bilibili API接口
 */
public interface BilibiliApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQuestions(String cookie) throws IOException;

    boolean answer(String cookie) throws IOException;

}

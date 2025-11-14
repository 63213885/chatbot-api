package com.chatbot.ai.domain.bilibili;

import com.chatbot.ai.domain.bilibili.model.aggregates.UnAnsweredQuestionsAggregates;
import com.chatbot.ai.domain.bilibili.model.request.AnswerRequest;

import java.io.IOException;

/**
 * Bilibili API接口
 */
public interface BilibiliApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQuestions(String cookie) throws IOException;

    boolean answer(String cookie, AnswerRequest answerRequest) throws IOException;

}

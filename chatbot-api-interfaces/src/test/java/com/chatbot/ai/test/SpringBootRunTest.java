package com.chatbot.ai.test;

import com.chatbot.ai.ApiApplication;
import com.chatbot.ai.domain.ai.Gemini;
import com.chatbot.ai.domain.bilibili.BilibiliApi;
import com.chatbot.ai.domain.bilibili.model.aggregates.UnAnsweredQuestionsAggregates;
import com.chatbot.ai.domain.bilibili.model.request.AnswerRequest;
import com.chatbot.ai.domain.bilibili.model.vo.Items;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class SpringBootRunTest {

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private BilibiliApi bilibiliApi;
    @Resource
    private Gemini memini;
    @Autowired
    private Gemini gemini;

    @Test
    public void test_bilibiliApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = bilibiliApi.queryUnAnsweredQuestions(cookie);
        log.info("UnAnsweredQuestionsAggregates: {}", unAnsweredQuestionsAggregates);

        List<Items> items = unAnsweredQuestionsAggregates.getData().getItems();

        ZoneId shanghaiZone = ZoneId.of("Asia/Shanghai");
        ZonedDateTime targetDateTime = ZonedDateTime.of(2025, 11, 14, 0, 0, 0, 0, shanghaiZone);
        log.info("targetDateTime: {}", targetDateTime);

        for (Items item : items) {
            Instant instant = Instant.ofEpochSecond(item.getAt_time());
            ZonedDateTime zonedDateTimeShanghai = instant.atZone(shanghaiZone);

            if (zonedDateTimeShanghai.isBefore(targetDateTime)) {
                continue;
            }
            log.info("zonedDateTimeShanghai: {}", zonedDateTimeShanghai);

            AnswerRequest answerRequest = AnswerRequest.builder()
                    .oid(item.getItem().getSubject_id())
                    .type(item.getItem().getBusiness_id())
                    // .message(item.getItem().getSource_content())
                    .message("11111111")
                    .scene("msg")
                    .plat(1L)
                    .from("im-reply")
                    .build(0L)
                    .mobi_app("web")
                    .root(item.getItem().getSource_id())
                    .parent(item.getItem().getSource_id())
                    .csrf("5231552cd07aba3546afe462d2ab1997")
                    .build();
            boolean answer = bilibiliApi.answer(cookie, answerRequest);
            log.info("answer: {}", answer);
        }
    }

    @Test
    public void test_gemini() throws IOException {
        String response = gemini.doGemini("用cpp写一个a+b");
        log.info("response: {}", response);
    }
}

package com.chatbot.ai.application.job;

import com.chatbot.ai.domain.ai.Gemini;
import com.chatbot.ai.domain.bilibili.BilibiliApi;
import com.chatbot.ai.domain.bilibili.model.aggregates.UnAnsweredQuestionsAggregates;
import com.chatbot.ai.domain.bilibili.model.request.AnswerRequest;
import com.chatbot.ai.domain.bilibili.model.vo.Items;
import com.chatbot.ai.domain.comment.model.entity.Comment;
import com.chatbot.ai.domain.comment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@EnableScheduling
@Configuration
@Slf4j
public class ChatBotSchedule {

    @Value("${chatbot-api.cookie}")
    private String COOKIE;
    @Value("${chatbot-api.csrf}")
    private String CSRF;

    @Resource
    private BilibiliApi bilibiliApi;
    @Resource
    private Gemini gemini;
    @Resource
    private CommentService commentService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void run() {
        log.info("任务自动执行中...");

        if (new Random().nextBoolean()) {
            log.info("随机打烊中...");
            return;
        }

        ZoneId shanghaiZone = ZoneId.of("Asia/Shanghai");
        ZonedDateTime now = ZonedDateTime.now(shanghaiZone);
        if (now.getHour() >= 23 || now.getHour() < 8) {
            log.info("下班ing...");
            return;
        }

        try {
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = bilibiliApi.queryUnAnsweredQuestions(COOKIE);
            log.info("UnAnsweredQuestionsAggregates: {}", unAnsweredQuestionsAggregates);

            List<Items> items = unAnsweredQuestionsAggregates.getData().getItems();
            if (items == null || items.size() == 0) {
                log.info("本次检索未查询到待回答问题");
                return;
            }

            ZonedDateTime targetDateTime = ZonedDateTime.of(2025, 11, 15, 0, 0, 0, 0, shanghaiZone);
            items = items.stream().filter(item -> {
                Instant instant = Instant.ofEpochSecond(item.getAt_time());
                ZonedDateTime dateTime = instant.atZone(shanghaiZone);
                return dateTime.isAfter(targetDateTime) && !commentService.isReplied(item.getId());
            }).collect(Collectors.toList());
            if (items == null || items.size() == 0) {
                log.info("本次检索未查询到待回答问题");
                return;
            }

            Items item = items.get(items.size() - 1);
            String question = item.getItem().getSource_content();
            question = question.replace("@_jzz_", "");
            log.info(question);

            String answer = gemini.doGemini(question);
            log.info(answer);

            AnswerRequest answerRequest = AnswerRequest.builder()
                    .oid(item.getItem().getSubject_id())
                    .type(item.getItem().getBusiness_id())
                    // .message(item.getItem().getSource_content())
                    .message(answer)
                    .scene("msg")
                    .plat(1L)
                    .from("im-reply")
                    .build(0L)
                    .mobi_app("web")
                    .root(item.getItem().getSource_id())
                    .parent(item.getItem().getSource_id())
                    .csrf(CSRF)
                    .build();
            boolean ok = bilibiliApi.answer(COOKIE, answerRequest);
            if (ok) {
                log.info("自动回复成功");
                boolean save = commentService.save(new Comment(null, item.getId()));
                log.info(save ? "数据库保存成功" : "数据库保存失败");
            } else {
                log.info("自动回复失败");
            }

        } catch (Exception e) {
            log.info("自动回答异常: ", e);
        }
    }

}

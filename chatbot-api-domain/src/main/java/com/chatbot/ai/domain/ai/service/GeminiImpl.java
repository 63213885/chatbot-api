package com.chatbot.ai.domain.ai.service;

import com.chatbot.ai.domain.ai.Gemini;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class GeminiImpl implements Gemini {

    @Value("${chatbot-api.gemini-api-key}")
    private String GEMINI_API_KEY;

    @Override
    public String doGemini(String question) throws IOException {
        question = question + "。你现在是一个在评论区回答问题的用户，请以这个角色的角度回答，" +
                "向你提问同样问题的可能是不同用户，不要带有语气等词语，" +
                "无需回复“好的”之类的词语，也无需解释过多，" +
                "请回答的内容言简意赅，通俗易懂。";
        Client client = Client.builder().apiKey(GEMINI_API_KEY).build();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        question,
                        null);
        return response.text();
    }
}

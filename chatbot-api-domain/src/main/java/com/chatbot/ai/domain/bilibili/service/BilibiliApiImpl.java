package com.chatbot.ai.domain.bilibili.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chatbot.ai.domain.bilibili.BilibiliApi;
import com.chatbot.ai.domain.bilibili.model.aggregates.UnAnsweredQuestionsAggregates;
import com.chatbot.ai.domain.bilibili.model.request.AnswerRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BilibiliApiImpl implements BilibiliApi {
    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestions(String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.bilibili.com/x/msgfeed/at?platform=web&build=0&mobi_app=web&web_location=333.40164");
        get.addHeader("cookie", cookie);
        get.addHeader("Content-Type", "application/json;charset=utf-8");
        get.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(jsonStr, UnAnsweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("Error: StatusCode = " + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String cookie, AnswerRequest answerRequest) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.bilibili.com/x/v2/reply/add");
        post.addHeader("cookie", cookie);
        post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        post.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/142.0.0.0 Safari/537.36");

        // 构建 Form Data (表单数据)
//        List<NameValuePair> formParams = new ArrayList<>();
//        formParams.add(new BasicNameValuePair("oid", "43694831")); // 视频/动态的ID
//        formParams.add(new BasicNameValuePair("type", "12")); // 类型 (1=视频, 12=专栏, 17=动态, etc.)
//        formParams.add(new BasicNameValuePair("message", "bbbbbbbbbbbbb")); // 你的评论内容
//        formParams.add(new BasicNameValuePair("scene", "msg"));
//        formParams.add(new BasicNameValuePair("plat", "1"));
//        formParams.add(new BasicNameValuePair("from", "im-reply"));
//        formParams.add(new BasicNameValuePair("build", "0"));
//        formParams.add(new BasicNameValuePair("mobi_app", "web"));
//        formParams.add(new BasicNameValuePair("root", "282273811648")); // 根评论ID (如果是回复别人的)
//        formParams.add(new BasicNameValuePair("parent", "282273811648")); // 父评论ID (如果是回复别人的)
//        formParams.add(new BasicNameValuePair("csrf", "5cb9e48553fca9650df71ed9601a8f75")); // CSRF 令牌

        // 将 Form Data 设置为请求的 Entity (实体)
        // 注意：这里使用 UrlEncodedFormEntity，而不是 StringEntity
        post.setEntity(new UrlEncodedFormEntity(answerRequest.toNameValuePairs(), "UTF-8"));

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            log.info("Response JsonObject: {}", jsonObject);

            String result = jsonObject.getJSONObject("data").getString("success_toast");
            log.info("result: {}", result);
            return "发送成功".equals(result);
        } else {
            log.info("请求失败！");
            throw new RuntimeException("Error: StatusCode = " + response.getStatusLine().getStatusCode());
        }
    }
}

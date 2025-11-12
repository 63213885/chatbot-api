package com.chatbot.ai.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 单元测试
 */
@Slf4j
@SpringBootApplication
public class ApiTest {

    @Test
    public void queryUnansweredQuestions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.bilibili.com/x/msgfeed/at?platform=web&build=0&mobi_app=web&web_location=333.40164");
        get.addHeader("cookie", "buvid3=42574459-7F3A-8132-6E09-B2AB1BF40DE634840infoc; b_nut=1755760834; _uuid=FB47A9AA-10BA8-5172-B643-BED10AFCD9FFA33509infoc; buvid_fp=8cc1fe6d7a8c22e6a36a82e1003ee068; enable_web_push=DISABLE; buvid4=D787CF79-FDCA-2F81-F422-1D757827E3C236776-025082115-9Xi6v+IK0ZvSWgGRevRnPmVE3lB3XaDnXGePWCVWr44YzTjoMBCbN7oWT36dxcg3; rpdid=0zbfvRWP5M|1blOgajNW|3i|3w1UOZLf; DedeUserID=1460774906; DedeUserID__ckMd5=40e6fa452680b77a; theme-tip-show=SHOWED; theme-avatar-tip-show=SHOWED; theme-switch-show=SHOWED; home_feed_column=5; CURRENT_QUALITY=80; PVID=1; LIVE_BUVID=AUTO9917580305155672; hit-dyn-v2=1; share_source_origin=COPY; bsource=share_source_copy_link; bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NjI5NTI0MjUsImlhdCI6MTc2MjY5MzE2NSwicGx0IjotMX0.zb7YOZk8yTN3qQh6CQZQUkYQEGDAsaLrJLpDomTOJx8; bili_ticket_expires=1762952365; SESSDATA=a3b82a2c%2C1778390786%2Cee5cc%2Ab1CjDlrXGpxnrIjRZ7cDTUIxYsahyL4qbNPuMdfQAPcGkIf-_OmfF9xLSt_gvrGLzaLw4SVnBlN19XTjNnUnU2MkFCOExXTVNzMnhzTG1HRWtOTFRkMlljZkxOUXNiMy0yLWFaeEZqbVdySl9OcXhZMVN0eThablNkTldRMlNpWm54V1NrT1JBbmZnIIEC; bili_jct=5cb9e48553fca9650df71ed9601a8f75; sid=4trf1im6; browser_resolution=1920-888; CURRENT_FNVAL=2000; bp_t_offset_1460774906=1134289829102616576; b_lsid=FF3B8B8E_19A7735975A");
        get.addHeader("Content-Type", "application/json;charset=utf-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String resp = EntityUtils.toString(response.getEntity());
            log.info("resp = {}", resp);
        } else {
            log.info("StatusCode = {}", response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.bilibili.com/x/v2/reply/add");
        post.addHeader("cookie", "buvid3=42574459-7F3A-8132-6E09-B2AB1BF40DE634840infoc; b_nut=1755760834; _uuid=FB47A9AA-10BA8-5172-B643-BED10AFCD9FFA33509infoc; buvid_fp=8cc1fe6d7a8c22e6a36a82e1003ee068; enable_web_push=DISABLE; buvid4=D787CF79-FDCA-2F81-F422-1D757827E3C236776-025082115-9Xi6v+IK0ZvSWgGRevRnPmVE3lB3XaDnXGePWCVWr44YzTjoMBCbN7oWT36dxcg3; rpdid=0zbfvRWP5M|1blOgajNW|3i|3w1UOZLf; DedeUserID=1460774906; DedeUserID__ckMd5=40e6fa452680b77a; theme-tip-show=SHOWED; theme-avatar-tip-show=SHOWED; theme-switch-show=SHOWED; home_feed_column=5; CURRENT_QUALITY=80; PVID=1; LIVE_BUVID=AUTO9917580305155672; hit-dyn-v2=1; share_source_origin=COPY; bsource=share_source_copy_link; bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3NjI5NTI0MjUsImlhdCI6MTc2MjY5MzE2NSwicGx0IjotMX0.zb7YOZk8yTN3qQh6CQZQUkYQEGDAsaLrJLpDomTOJx8; bili_ticket_expires=1762952365; SESSDATA=a3b82a2c%2C1778390786%2Cee5cc%2Ab1CjDlrXGpxnrIjRZ7cDTUIxYsahyL4qbNPuMdfQAPcGkIf-_OmfF9xLSt_gvrGLzaLw4SVnBlN19XTjNnUnU2MkFCOExXTVNzMnhzTG1HRWtOTFRkMlljZkxOUXNiMy0yLWFaeEZqbVdySl9OcXhZMVN0eThablNkTldRMlNpWm54V1NrT1JBbmZnIIEC; bili_jct=5cb9e48553fca9650df71ed9601a8f75; sid=4trf1im6; browser_resolution=1920-888; CURRENT_FNVAL=2000; bp_t_offset_1460774906=1134289829102616576; b_lsid=FF3B8B8E_19A7735975A");
        post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 构建 Form Data (表单数据)
        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("oid", "43694831")); // 视频/动态的ID
        formParams.add(new BasicNameValuePair("type", "12")); // 类型 (1=视频, 12=专栏, 17=动态, etc.)
        formParams.add(new BasicNameValuePair("message", "bbbbbbbbbbbbb")); // 你的评论内容
        formParams.add(new BasicNameValuePair("scene", "msg"));
        formParams.add(new BasicNameValuePair("plat", "1"));
        formParams.add(new BasicNameValuePair("from", "im-reply"));
        formParams.add(new BasicNameValuePair("build", "0"));
        formParams.add(new BasicNameValuePair("mobi_app", "web"));
        formParams.add(new BasicNameValuePair("root", "282273811648")); // 根评论ID (如果是回复别人的)
        formParams.add(new BasicNameValuePair("parent", "282273811648")); // 父评论ID (如果是回复别人的)
        formParams.add(new BasicNameValuePair("csrf", "5cb9e48553fca9650df71ed9601a8f75")); // CSRF 令牌

        // 将 Form Data 设置为请求的 Entity (实体)
        // 注意：这里使用 UrlEncodedFormEntity，而不是 StringEntity
        post.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String resp = EntityUtils.toString(response.getEntity());
            log.info("resp = {}", resp);
        } else {
            log.info("StatusCode = {}", response.getStatusLine().getStatusCode());
        }
    }

}

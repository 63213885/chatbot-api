package com.chatbot.ai.domain.bilibili.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerRequest {
    private Long oid;
    private Long type;
    private String message;
    private String scene;
    private Long plat;
    private String from;
    private Long build;
    private String mobi_app;
    private Long root;
    private Long parent;
    private String csrf;

    /**
     * 辅助方法：将 DTO 转换为 HttpClient 需要的 List<NameValuePair>
     */
    public List<NameValuePair> toNameValuePairs() {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("oid", String.valueOf(this.oid)));
        params.add(new BasicNameValuePair("type", String.valueOf(this.type)));
        params.add(new BasicNameValuePair("message", this.message));
        params.add(new BasicNameValuePair("scene", this.scene));
        params.add(new BasicNameValuePair("plat", String.valueOf(this.plat)));
        params.add(new BasicNameValuePair("from", this.from));
        params.add(new BasicNameValuePair("build", String.valueOf(this.build)));
        params.add(new BasicNameValuePair("mobi_app", this.mobi_app));
        params.add(new BasicNameValuePair("root", String.valueOf(this.root)));
        params.add(new BasicNameValuePair("parent", String.valueOf(this.parent)));
        params.add(new BasicNameValuePair("csrf", this.csrf));
        return params;
    }
}

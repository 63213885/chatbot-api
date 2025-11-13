package com.chatbot.ai.domain.bilibili.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private String type;

    private String business;

    private Integer business_id;

    private String title;

    private String image;

    private String uri;

    private Integer subject_id;

    private Integer root_id;

    private Integer target_id;

    private Integer source_id;

    private String source_content;

    private String native_uri;

    private List<At_details> at_details;

    private List<String> topic_details;

    private Boolean hide_reply_button;

}

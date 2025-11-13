package com.chatbot.ai.domain.bilibili.model.vo;

import java.util.List;

@lombok.Data
public class Data {

    private Cursor cursor;

    private List<Items> items;

}

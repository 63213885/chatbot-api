package com.chatbot.ai.domain.bilibili.model.response;

import com.chatbot.ai.domain.bilibili.model.vo.Cursor;
import com.chatbot.ai.domain.bilibili.model.vo.Items;

import java.util.List;

@lombok.Data
public class Data {

    private Cursor cursor;

    private List<Items> items;

}

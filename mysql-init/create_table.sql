# 数据库初始化

-- 创建库
create database if not exists bilibili_reply;

-- 切换库
use bilibili_reply;

-- 用户表
create table if not exists comment
(
    id           bigint auto_increment comment 'id' primary key,
    commentId    bigint                             not null comment '记录回复过的评论id',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    unique index idx_unionId (commentId)
) comment '已回复评论' collate = utf8mb4_unicode_ci;

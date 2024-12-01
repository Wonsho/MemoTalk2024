package com.wons.memotalk.entity;

enum MemoValueCategory {
    TEXT(1L),
    IMG(2L),
    URL(3L),
    FILE(4L),
    TODO(5L);

    private long category;

     MemoValueCategory(long category) {
        this.category = category;
    }
}

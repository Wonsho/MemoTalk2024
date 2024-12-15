package com.wons.memotalk.entity.memo_data;

public enum MemoType {
    TEXT(1),
    IMG(2),
    TODO(3),
    FILE(4),
    URL(5);

    private int type;

    MemoType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}

package com.wons.memotalk.entity.memo_data;

public enum MemoType {
    TEXT(1),
    IMG(2),
    TODO(3),
    FILE(4),
    URL(5);

    private int typeCode;

    MemoType(int typeCode) {
        this.typeCode = typeCode;
    }

    public static MemoType getType(int code) {
        MemoType[] memoTypes = MemoType.values();

        for (MemoType memoType : memoTypes) {
            if (memoType.typeCode == code) {
                return memoType;
            }
        }
        throw new RuntimeException("type is not allow");
    }

    public int getCode() {
        return this.typeCode;
    }
}

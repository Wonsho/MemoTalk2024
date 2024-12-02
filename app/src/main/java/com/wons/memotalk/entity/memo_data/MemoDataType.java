package com.wons.memotalk.entity.memo_data;

public enum MemoDataType {
    TEXT(1),
    IMAGE(2),
    FILE(3),
    TODO(4),
    URL(5);

    private int typeCode;

    MemoDataType(int type) {
        this.typeCode = type;
    }

    public int getTypeCode() {
        return this.typeCode;
    }

    public static MemoDataType fromTypeCode(int typeCode) {
        for (MemoDataType type : values()) {
            if (type.typeCode == typeCode) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid type code: " + typeCode);
    }

}

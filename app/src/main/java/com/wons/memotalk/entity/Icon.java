package com.wons.memotalk.entity;

public class Icon {
    //리스트 아이디 참조
    private long id;
    private String iconName;

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

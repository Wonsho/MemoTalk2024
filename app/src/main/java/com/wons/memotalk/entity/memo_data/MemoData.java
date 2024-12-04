package com.wons.memotalk.entity.memo_data;


import com.wons.memotalk.util.DateUtil;

//메모 아이템 데이터
public class MemoData {
    private final Long id;
    private final Integer category;
    private final Object itemData;
    private final Long date;
    private Boolean check;
    private Boolean exclamation;

    public MemoData(Long id, Integer category, Object itemData, Long date, Boolean check, Boolean exclamation) {
        this.id = id;
        this.category = category;
        this.itemData = itemData;
        this.date = date;
        this.check = check;
        this.exclamation = exclamation;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public void setExclamation(Boolean exclamation) {
        this.exclamation = exclamation;
    }

    public Long getId() {
        return this.id;
    }

    public Integer getCategory() {
        return this.category;
    }

    public Object getItemData() {
        return this.itemData;
    }

    public String getDate(String format) {
        return DateUtil.dateFormat(format, this.date);
    }

    public Boolean getCheck() {
        return this.check;
    }

    public Boolean getExclamation() {
        return this.exclamation;
    }
}

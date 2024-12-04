package com.wons.memotalk.entity.memoList;

public class MainMemoListModel {
    private MainMemoList memoListItem;
    private String lastMemoItemValue;
    private Long date;

    public MainMemoList getMemoListItem() {
        return memoListItem;
    }

    public void setMemoListItem(MainMemoList memoListItem) {
        this.memoListItem = memoListItem;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getLastMemoItemValue() {
        return lastMemoItemValue;
    }

    public void setLastMemoItemValue(String lastMemoItemValue) {
        this.lastMemoItemValue = lastMemoItemValue;
    }
}

package com.wons.memotalk.entity.memo_data;


//메모 아이템 데이터
public class MemoData {
    private Long id;
    public Integer category;
    public Object itemData;
    public Long date;
    private Boolean check;
    private Boolean exclamation;

    public Long getId() {
        return this.id;
    }

    public Integer getCategory() {
        return this.category;
    }

    public Object getItemData() {
        return this.itemData;
    }

    public Long getDate() {
        return this.date;
    }

    public Boolean getCheck() {
        return this.check;
    }

    public Boolean getExclamation() {
        return this.exclamation;
    }


    public static class MemoDataBuilder {
        private MemoData memoData;

        private void setDataIfNull() {
            memoData = new MemoData();
        }

        public MemoDataBuilder setID(Long id) {
            setDataIfNull();
            memoData.id = id;
            return this;
        }

        public MemoDataBuilder setCategory(Integer category) {
            setDataIfNull();
            memoData.category = category;
            return this;
        }

        public MemoDataBuilder setItemData(Object data) {
            setDataIfNull();
            memoData.itemData = data;
            return this;
        }

        public MemoDataBuilder setDate(Long date) {
            setDataIfNull();
            memoData.date = date;
            return this;
        }

        public MemoDataBuilder setCheck(Boolean check) {
            setDataIfNull();
            memoData.check = check;
            return this;
        }

        public MemoDataBuilder setExclamation(Boolean exclamation) {
            setDataIfNull();
            memoData.exclamation = exclamation;
            return this;
        }

        public MemoData build() {
            MemoData memoData1 = this.memoData;
            this.memoData = null;
            return memoData1;
        }
    }
}

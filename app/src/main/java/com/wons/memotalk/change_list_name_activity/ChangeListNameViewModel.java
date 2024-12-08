package com.wons.memotalk.change_list_name_activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChangeListNameViewModel extends ViewModel {
    private MutableLiveData<Info> data;

    private class Info {
        private String listName;
        private Long listId;

    }

    public void setListData(String listName,Long listId) {
        if (this.data == null) {
            this.data = new MutableLiveData<>();
            this.data.setValue(new Info());
        }
        this.data.getValue().listName = listName;
        this.data.getValue().listId = listId;
    }

    public String getTitle() {
        return this.data.getValue().listName;
    }

    public Long getListId() {
        return this.data.getValue().listId;
    }
}

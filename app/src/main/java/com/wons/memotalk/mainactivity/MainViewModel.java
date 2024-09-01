package com.wons.memotalk.mainactivity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wons.memotalk.mainactivity.livedata.AddTabState;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<AddTabState> tabUiState =
            new MutableLiveData<>(new AddTabState());

    public MutableLiveData<AddTabState> getTabUiState() {
        return this.tabUiState;
    }
}

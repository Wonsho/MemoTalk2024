package com.wons.memotalk.mainactivity.livedata;

public class AddTabState {
    private boolean show;
    private String tabTitle;
    private int stateSize;

    public AddTabState() {
        this.show = false;
        this.tabTitle = "";
        this.stateSize = 0;
    }

    public void setStateSize(int stateSize) {
         this.stateSize = stateSize;
    }

    public void setShow(boolean c) {
        this.show = c;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }

    public boolean getShow() {
        return this.show;
    }

    public String getTabTitle() {
        return this.tabTitle;
    }

    public int getStateSize() {
        return this.stateSize;
    }
}

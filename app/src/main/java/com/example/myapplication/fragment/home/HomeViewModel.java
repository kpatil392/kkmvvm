package com.example.myapplication.fragment.home;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.base.BaseViewModel;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {
    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    private String tvName;
    public void onNavBackClick()
    {
        getNavigator().goBack();
    }
  
}

package com.example.myapplication.fragment.home;

import android.util.Log;
import android.widget.Toast;

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
    
    public void createNoti()
    {
        getNavigator().createNotification();
        Log.i("Noti","create");
        
    } 
    public void cancelNoti()
    {
        Log.i("Noti","cancel");
        getNavigator().cancelNotification();
    }
    public void singleNoti()
    {
        getNavigator().singleNotification();
    } 
    public void bundleNoti()
    {
        getNavigator().bundleNotification();
    }
    
  
}

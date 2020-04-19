package com.example.myapplication.splash;

import com.example.myapplication.base.BaseViewModel;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {
    @Override
    protected void onCleared() {
        super.onCleared();
    }
    public void decideNextActivity() {
            getNavigator().openSplashActivity();
    }
}

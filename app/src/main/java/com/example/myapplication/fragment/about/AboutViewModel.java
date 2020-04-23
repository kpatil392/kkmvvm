package com.example.myapplication.fragment.about;

import com.example.myapplication.base.BaseViewModel;

public class AboutViewModel extends BaseViewModel<AboutNavigator> {
    @Override
    protected void onCleared() {
        super.onCleared();
    }
    public void onNavBackClick()
    {
        getNavigator().goBack();
    }
}

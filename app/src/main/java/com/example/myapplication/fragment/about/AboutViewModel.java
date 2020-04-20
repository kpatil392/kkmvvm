package com.example.myapplication.fragment.about;

import com.example.myapplication.base.BaseViewModel;

public class AboutViewModel extends BaseViewModel<AboutNAvigator> {
    @Override
    protected void onCleared() {
        super.onCleared();
    }
    public void onNavBackClick()
    {
        getNavigator().goBack();
    }
}

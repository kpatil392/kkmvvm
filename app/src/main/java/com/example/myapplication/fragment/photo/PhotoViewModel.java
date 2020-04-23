package com.example.myapplication.fragment.photo;

import com.example.myapplication.base.BaseViewModel;

public class PhotoViewModel extends BaseViewModel<PhotoNavigator> {
    @Override
    protected void onCleared() {
        super.onCleared();
    }
    public void onNavBackClick()
    {
        getNavigator().goBack();
    }
}

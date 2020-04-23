package com.example.myapplication.home;


import android.text.TextUtils;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.base.BaseViewModel;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {
    private final ObservableField<String> appVersion = new ObservableField<>();
    private final ObservableField<String> username = new ObservableField<>();
    
    public ObservableField<String> getUsername() {
        return username;
    }

    public ObservableField<String> getUserEmail() {
        return userEmail;
    }

    private final ObservableField<String> userEmail = new ObservableField<>();


    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void onNavMenuCreated() {
        username.set("Kundan Patil");
        userEmail.set("abc@gmail.com");
        /*if (!TextUtils.isEmpty(username)) {
            userName.set(currentUserName);
        }

        final String currentUserEmail = getDataManager().getCurrentUserEmail();
        if (!TextUtils.isEmpty(currentUserEmail)) {
            userEmail.set(currentUserEmail);
        }

        final String profilePicUrl = getDataManager().getCurrentUserProfilePicUrl();
        if (!TextUtils.isEmpty(profilePicUrl)) {
            userProfilePicUrl.set(profilePicUrl);
        }*/
    }

    public void updateAppVersion(String version) {
        appVersion.set(version);
    }

    public ObservableField<String> getAppVersion() {
        return appVersion;
    }
}

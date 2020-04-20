package com.example.myapplication.home;


import androidx.databinding.ObservableField;

import com.example.myapplication.base.BaseViewModel;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {
    private final ObservableField<String> appVersion = new ObservableField<>();
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void onNavMenuCreated() {
        //final String currentUserName = getDataManager().getCurrentUserName();
      /*  if (!TextUtils.isEmpty(currentUserName)) {
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
    }}

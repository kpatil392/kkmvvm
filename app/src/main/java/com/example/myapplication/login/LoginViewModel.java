package com.example.myapplication.login;

import android.text.TextUtils;

import com.example.myapplication.base.BaseViewModel;
import com.example.myapplication.utils.CommonUtils;

public class LoginViewModel extends BaseViewModel<LoginNavigator>{
    @Override
    protected void onCleared() {
        super.onCleared();
    }
    public boolean isEmailAndPasswordValid(String email, String password) {
        // validate email and password
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        if (!CommonUtils.isEmailValid(email)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }
    public void login(String email, String password) {
        setIsLoading(true);
        if(email.equals("1234@gmail.com") && password.equals("1234"))
        {
            setIsLoading(false);
            getNavigator().openMainActivity();
        }else {
            setIsLoading(false);
            getNavigator().invalidUser();
        }
        
    }
    public void onServerLoginClick() {
        getNavigator().login();
    }
}

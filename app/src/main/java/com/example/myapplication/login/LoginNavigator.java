package com.example.myapplication.login;

public interface LoginNavigator {
    void handleError(Throwable throwable);

    void login();

    void openMainActivity();
}

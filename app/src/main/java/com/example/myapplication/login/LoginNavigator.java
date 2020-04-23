package com.example.myapplication.login;

public interface LoginNavigator {
    void handleError(Throwable throwable);
    void invalidUser();

    void login();

    void openMainActivity();
}

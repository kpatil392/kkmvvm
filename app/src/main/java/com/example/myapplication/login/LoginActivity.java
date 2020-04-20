package com.example.myapplication.login;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.databinding.LoginActivityBinding;

public class LoginActivity extends BaseActivity<LoginActivityBinding,LoginViewModel> implements LoginNavigator {
    LoginViewModel mLoginViewModel;
    @Override
    public int getBindingVariable() {
        return BR.loginviewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getSupportActionBar().hide();
        mLoginViewModel.setNavigator(this);
       
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void handleError(Throwable throwable) {
        
    }

    @Override
    public void login() {

    }

    @Override
    public void openMainActivity() {

    }
}

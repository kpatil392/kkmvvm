package com.example.myapplication.login;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.databinding.MainActivityBinding;

public class LoginActivity extends BaseActivity<MainActivityBinding,LoginViewModel> {
    LoginViewModel mLoginViewModel;
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}

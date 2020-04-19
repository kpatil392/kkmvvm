package com.example.myapplication.splash;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.databinding.MainActivityBinding;
import com.example.myapplication.databinding.SplashActivityBinding;

public class SplashActivity extends BaseActivity<SplashActivityBinding, SplashViewModel> {
    SplashViewModel mLoginViewModel;
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.splash_activity;
    }

    @Override
    public SplashViewModel getViewModel() {
        mLoginViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
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

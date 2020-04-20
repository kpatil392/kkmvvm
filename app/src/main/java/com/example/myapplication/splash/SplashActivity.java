package com.example.myapplication.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import com.example.myapplication.databinding.SplashActivityBinding;
import com.example.myapplication.home.HomeActivity;
import com.example.myapplication.login.LoginActivity;

public class SplashActivity extends BaseActivity<SplashActivityBinding, SplashViewModel> implements SplashNavigator{
    SplashViewModel mSplashViewModel;
    @Override
    public int getBindingVariable() {
        return BR.splashviewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.splash_activity;
    }

    @Override
    public SplashViewModel getViewModel() {
        mSplashViewModel = ViewModelProviders.of(this).get(SplashViewModel.class);
        return mSplashViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mSplashViewModel.setNavigator(this);
        mSplashViewModel.decideNextActivity();
       
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void openSplashActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            }
        },3000);
    }
}

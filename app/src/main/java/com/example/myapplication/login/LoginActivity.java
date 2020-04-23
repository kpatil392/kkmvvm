package com.example.myapplication.login;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.databinding.LoginActivityBinding;
import com.example.myapplication.home.HomeActivity;

public class LoginActivity extends BaseActivity<LoginActivityBinding,LoginViewModel> implements LoginNavigator {
    private LoginViewModel mLoginViewModel;
    private LoginActivityBinding mLoginActivityBinding;
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
        getSupportActionBar().hide();
        mLoginActivityBinding = getViewDataBinding();
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
    public void invalidUser() {
        Toast.makeText(this,"Invalid user",Toast.LENGTH_LONG).show();
    }

    @Override
    public void login() {
        String email =mLoginActivityBinding.etEmail.getText().toString();
        String password = mLoginActivityBinding.etPassword.getText().toString();
        if (mLoginViewModel.isEmailAndPasswordValid(email, password)) {
            hideKeyboard();
            mLoginViewModel.login(email, password);
        } else {
            Toast.makeText(this, getString(R.string.invalid_email_password), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void openMainActivity() {
        Toast.makeText(this, "nhhh", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

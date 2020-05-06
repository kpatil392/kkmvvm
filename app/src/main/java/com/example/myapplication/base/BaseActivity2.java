package com.example.myapplication.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.myapplication.permissiio.PermissionUtil;
import com.example.myapplication.utils.CommonUtils;
import com.example.myapplication.utils.NetworkUtils;


public abstract class BaseActivity2 extends AppCompatActivity{


    //permission

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionUtil.onRequestPermissionResult(this, requestCode, permissions, grantResults);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        PermissionUtil.onActivityResult(this, requestCode);
    }
}


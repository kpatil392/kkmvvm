package com.example.myapplication.base;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel<N> extends ViewModel {

   
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    }
package com.example.myapplication.fragment.product;

import com.example.myapplication.base.BaseViewModel;

public class ProductViewModel extends BaseViewModel<ProductNavigator> {
    @Override
    protected void onCleared() {
        super.onCleared();
    }
    public void onNavBackClick()
    {
        getNavigator().goBack();
    }
}

package com.example.myapplication.fragment.product;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.databinding.FragmentProductBinding;
import com.example.myapplication.fragment.about.AboutFragment;

public class ProductFragment extends BaseFragment<FragmentProductBinding,ProductViewModel> implements ProductNavigator {
    public static final String TAG = AboutFragment.class.getSimpleName();

    private  ProductViewModel mProductViewModel;
    @Override
    public int getBindingVariable() {
        return BR.productviewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product;
    }

    @Override
    public ProductViewModel getViewModel() {
        mProductViewModel= ViewModelProviders.of(this).get(ProductViewModel.class) ;
        return mProductViewModel;
    }
    public static ProductFragment newInstance() {
        Bundle args = new Bundle();
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductViewModel.setNavigator(this);
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }
}

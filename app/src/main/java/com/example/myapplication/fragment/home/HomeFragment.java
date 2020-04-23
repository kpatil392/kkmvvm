package com.example.myapplication.fragment.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.utils.InputValidations;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements HomeNavigator {
    public static final String TAG = HomeFragment.class.getSimpleName();
    private HomeViewModel mHomeViewModel;
    InputValidations mInputValidations;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getBindingVariable() {
        return BR.homeviewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        mHomeViewModel= ViewModelProviders.of(this).get(HomeViewModel.class);
        return mHomeViewModel;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeViewModel.setNavigator(this);
        mInputValidations=new InputValidations(getBaseActivity());
        mHomeViewModel.setTvName(mInputValidations.getAge("03031992")+"");
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }
}

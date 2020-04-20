package com.example.myapplication.fragment.about;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.databinding.FragmentAboutBinding;

public class AboutFragment extends BaseFragment<FragmentAboutBinding,AboutViewModel> implements AboutNAvigator {
    public static final String TAG = AboutFragment.class.getSimpleName();
    private AboutViewModel mAboutViewModel;

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getBindingVariable() {
        return BR.homeviewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    public AboutViewModel getViewModel() {
        mAboutViewModel= ViewModelProviders.of(this).get(AboutViewModel.class);
        return mAboutViewModel;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAboutViewModel.setNavigator(this);
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }
}

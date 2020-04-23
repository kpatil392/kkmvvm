package com.example.myapplication.fragment.photo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.databinding.FragmentAboutBinding;
import com.example.myapplication.databinding.FragmentPhotoBinding;

public class PhotoFragment extends BaseFragment<FragmentPhotoBinding, PhotoViewModel> implements PhotoNavigator {
    public static final String TAG = PhotoFragment.class.getSimpleName();
    private PhotoViewModel mPhotoViewModel;

    public static PhotoFragment newInstance() {
        Bundle args = new Bundle();
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getBindingVariable() {
        return BR.photoviewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_photo;
    }

    @Override
    public PhotoViewModel getViewModel() {
        mPhotoViewModel= ViewModelProviders.of(this).get(PhotoViewModel.class);
        return mPhotoViewModel;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoViewModel.setNavigator(this);
       
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }
}

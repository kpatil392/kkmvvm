package com.example.myapplication.fragment.product;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.databinding.FragmentProductBinding;
import com.example.myapplication.fragment.about.AboutFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ProductFragment extends BaseFragment<FragmentProductBinding,ProductViewModel> implements ProductNavigator {
    public static final String TAG = AboutFragment.class.getSimpleName();
    FragmentProductBinding binding;
    private BottomSheetDialog mBottomSheetDialog;
    private BottomSheetBehavior mDialogBehavior;
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
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding=getViewDataBinding();
        binding.btn1.setText("Kundan");

     /* mDialogBehavior = BottomSheetBehavior.from(binding.bottomSheet);
        mDialogBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //showBottomSheetDialog();
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN://5
                        Log.i("BottomSheet","STATE_HIDDEN="+newState+"="+BottomSheetBehavior.STATE_HIDDEN);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED://3
                        //showBottomSheetView();
                        Log.i("BottomSheet","STATE_EXPANDED="+newState+"="+BottomSheetBehavior.STATE_EXPANDED);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED://4
                        Log.i("BottomSheet","STATE_COLLAPSED="+newState+"="+BottomSheetBehavior.STATE_COLLAPSED);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING://1
                        Log.i("BottomSheet","STATE_DRAGGING="+newState+"="+BottomSheetBehavior.STATE_DRAGGING);
                        break;
                    case BottomSheetBehavior.STATE_SETTLING://2
                        Log.i("BottomSheet","STATE_SETTLING="+newState+"="+BottomSheetBehavior.STATE_SETTLING);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
               // showBottomSheetView();
                //Log.i("BottomSheet","slideOffset="+slideOffset);
            }
        });*/
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

    @Override
    public void btn1() {
        showBottomSheet();
       }

    @Override
    public void btn2() {
       // showBottomSheetDialog();

    }

    ///
    private void showBottomSheet() {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getActivity());
        View sheetView = getActivity().getLayoutInflater().inflate(R.layout.bs_fragment_history_bottomsheet, null);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();
        LinearLayout edit = (LinearLayout) sheetView.findViewById(R.id.fragment_history_bottom_sheet_edit);
        LinearLayout delete = (LinearLayout) sheetView.findViewById(R.id.fragment_history_bottom_sheet_delete);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseActivity(), "Edit", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseActivity(), "Delete", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showBottomSheetView() {
        mDialogBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
        }
    }
    private void showBottomSheetDialog() {
        if (mDialogBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mDialogBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        View view = getLayoutInflater().inflate(R.layout.bs_sheet, null);
        

        mBottomSheetDialog = new BottomSheetDialog(getBaseActivity());
        mBottomSheetDialog.setContentView(view);
        mDialogBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBottomSheetDialog.show();
        
    }

   /* private void showBottomSheetDialogFullscreen() {
        new FullBottomSheetDialogFragment().show(getSupportFragmentManager(), "dialog");
    }*/
}

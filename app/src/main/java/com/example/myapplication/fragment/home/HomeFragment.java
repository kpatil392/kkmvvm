package com.example.myapplication.fragment.home;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.home.HomeActivity;
import com.example.myapplication.notification.NotificationHandler;
import com.example.myapplication.notification.NotificationUtils;
import com.example.myapplication.utils.InputValidations;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.example.myapplication.notification.NotificationUtils.ANDROID_CHANNEL_ID;

@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements HomeNavigator {
    public static final String TAG = HomeFragment.class.getSimpleName();
    String NOTI_ID = "kkkk";
    NotificationUtils utils;
    String NOTIFICATION_ID = "Kiru";
    //NotificationHandler nHandler; 
    private HomeViewModel mHomeViewModel;
    InputValidations mInputValidations;
    NotificationManager mNotificationManager;

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
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        return mHomeViewModel;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeViewModel.setNavigator(this);
        //nHandler=NotificationHandler.getInstance(getBaseActivity());
        mInputValidations = new InputValidations(getBaseActivity());
        mHomeViewModel.setTvName(mInputValidations.getAge("1992-03-03") + "");
        utils = new NotificationUtils(getBaseActivity());
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void createNotification() {
        //makenoti("hello kundan nhjkhkj jhbjh djwbjdw  dwbdw dnwbdnwedw ebwje wenwhbvewe wewhbew ewnvenw ewbevw ewbevw ebwevw ewbvebwe webvwb ebwvewb ewbevw ebw");
        // makenotiorio("hello kundan nhjkhkj jhbjh djwbjdw  dwbdw dnwbdnwedw ebwje wenwhbvewe wewhbew ewnvenw ewbevw ewbevw ebwevw ewbvebwe webvwb ebwvewb ewbevw ebw");
        //NotificationCompat.Builder nb = utils.getSimpleNotification("hello","hghg dhsgvdh hvge ehwge ");
        /*NotificationCompat.Builder nb = utils.getLargeIconWithImageNotification("hello",getResources().getString(R.string.lorem_ipsum));//"hghg dhsgvdh hvge ehwge hjk dkjshdjs\\. djhsgds dsjdgbs dsnhds dnhsdv sdbsdsnvd  sdbsn dnsbd sd ns d nsd s dvsnbd ");
        utils.getManager().notify(101, nb.build());*/
        //utils.createProgressNotification(getBaseActivity());
        //utils.createButtonNotification(getBaseActivity());
        utils.getLargeTextSetActionNotification("Hello Kundan & Nikkku", getResources().getString(R.string.lorem_ipsum), "https://cloud.netlifyusercontent.com/assets/344dbf88-fdf9-42bb-adb4-46f01eedd629/9cc3d6eb-5da6-4067-b091-1071009a9670/app-ios-12-notifications-hiddenpreviewgroup.png", getBaseActivity());//https://i.stack.imgur.com/x8PhM.png");
        //utils.GroupNotification("Hello Kundan & Nikkku",getResources().getString(R.string.lorem_ipsum));
    }

    @Override
    public void cancelNotification() {
        utils.getLargeIconWithImageNotification("Hello cancel ", getResources().getString(R.string.lorem_ipsum), getBaseActivity());//,"https://cloud.netlifyusercontent.com/assets/344dbf88-fdf9-42bb-adb4-46f01eedd629/9cc3d6eb-5da6-4067-b091-1071009a9670/app-ios-12-notifications-hiddenpreviewgroup.png");//https://i.stack.imgur.com/x8PhM.png");
        //utils.GroupNotification("Hello cancel ",getResources().getString(R.string.lorem_ipsum));//,"https://cloud.netlifyusercontent.com/assets/344dbf88-fdf9-42bb-adb4-46f01eedd629/9cc3d6eb-5da6-4067-b091-1071009a9670/app-ios-12-notifications-hiddenpreviewgroup.png");//https://i.stack.imgur.com/x8PhM.png");

    }

    @Override
    public void singleNotification() {

    }

    @Override
    public void bundleNotification() {
        utils.GroupNotification("Hello Kundan", getResources().getString(R.string.lorem_ipsum), getBaseActivity());
    }


}

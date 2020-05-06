package com.example.myapplication.home;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewbinding.BuildConfig;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.databinding.HomeActivityBinding;
import com.example.myapplication.databinding.NavHeaderMainBinding;

import com.example.myapplication.fragment.about.AboutFragment;
import com.example.myapplication.fragment.home.HomeFragment;
import com.example.myapplication.fragment.product.ProductFragment;
import com.google.android.material.navigation.NavigationView;
@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeActivity extends BaseActivity<HomeActivityBinding, HomeViewModel> implements HomeNavigator {

    // index to identify current nav menu item
    public static int navItemIndex = 0;
    private boolean shouldLoadHomeFragOnBackPress = true;
    private String[] activityTitles;
    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;
    
    private Handler mHandler;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private HomeViewModel mHomeViewModel;
    private HomeActivityBinding mHomeActivityBinding;
   /* public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }*/
    @Override
    public int getBindingVariable() {
        return BR.homeviewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    public HomeViewModel getViewModel() {
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        return mHomeViewModel;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openLoginActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeActivityBinding = getViewDataBinding();
        mHomeViewModel.setNavigator(this);
        mHandler = new Handler();
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        setUp();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
        
    }

    private void setUp() {
        mDrawer = mHomeActivityBinding.drawerView;
        mToolbar = mHomeActivityBinding.appbar.toolbar;
        mNavigationView = mHomeActivityBinding.navigationView;
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setUpNavigationView();
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        mHomeViewModel.updateAppVersion(version);
        mHomeViewModel.onNavMenuCreated();
        
    }

    private void setUpNavigationView() {
        NavHeaderMainBinding navHeaderMainBinding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.nav_header_main, mHomeActivityBinding.navigationView, false);
        mHomeActivityBinding.navigationView.addHeaderView(navHeaderMainBinding.getRoot());
        navHeaderMainBinding.setHomeviewModel(mHomeViewModel);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        mDrawer.closeDrawer(GravityCompat.START);
                        switch (item.getItemId()) {
                            case R.id.navItemAbout:
                                navItemIndex = 0;
                                CURRENT_TAG = TAG_HOME;
                                break;
                            case R.id.navItemRateUs:
                                navItemIndex = 1;
                                CURRENT_TAG = TAG_PHOTOS;
                                break;
                            case R.id.navItemFeed:
                                navItemIndex = 2;
                                CURRENT_TAG = TAG_MOVIES;
                                break;
                            case R.id.navItemLogout:
                                navItemIndex = 3;
                                CURRENT_TAG = TAG_NOTIFICATIONS;
                                break;
                            default:
                                return false;

                        }
                        if (item.isChecked()) {
                            item.setChecked(false);
                        } else {
                            item.setChecked(true);
                        }
                        item.setChecked(true);
                        loadHomeFragment();
                        return true;
                        
                    }
                });
    }

  
    private void unlockDrawer() {
        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    private void lockDrawer() {
        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    

    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }
    private void loadHomeFragment() {
           setToolbarTitle();
           selectNavMenu();
           if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            mDrawer.closeDrawers();
            // show or hide the fab button
            return;
        }

      
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
        
        //Closing drawer on item click
        mDrawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private void selectNavMenu() {
        mNavigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                // photos
                AboutFragment photosFragment = new AboutFragment();
                return photosFragment;
            case 2:
                // movies fragment
                AboutFragment moviesFragment = new AboutFragment();
                return moviesFragment;
            case 3:
                // notifications fragment
                ProductFragment notificationsFragment = new ProductFragment();
                return notificationsFragment;

            case 4:
                // settings fragment
                AboutFragment settingsFragment = new AboutFragment();
                return settingsFragment;
            default:
                return new HomeFragment();
        }
    }
    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }
}

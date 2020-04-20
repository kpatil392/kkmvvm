package com.example.myapplication.home;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewbinding.BuildConfig;

import com.example.myapplication.BR;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.databinding.HomeActivityBinding;
import com.example.myapplication.databinding.NavHeaderMainBinding;
import com.example.myapplication.fragment.about.AboutFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends BaseActivity<HomeActivityBinding, HomeViewModel> implements HomeNavigator {

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private HomeViewModel mHomeViewModel;
    private HomeActivityBinding mHomeActivityBinding;

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
        setUp();
    }

    private void setUp() {
        mDrawer = mHomeActivityBinding.drawerView;
        mToolbar = mHomeActivityBinding.toolbar;
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
        setupNavMenu();
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        mHomeViewModel.updateAppVersion(version);
        mHomeViewModel.onNavMenuCreated();
        //showProductFragment();
       /* setupCardContainerView();
        subscribeToLiveData();*/
    }

    private void setupNavMenu() {
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
                                showAboutFragment();
                                return true;
                            case R.id.navItemRateUs:
                               // showProductFragment();
                                Toast.makeText(HomeActivity.this, "tem Rate", Toast.LENGTH_LONG).show();
                                //  RateUsDialog.newInstance().show(getSupportFragmentManager());
                                return true;
                            case R.id.navItemFeed:
                                Toast.makeText(HomeActivity.this, "tem Rate", Toast.LENGTH_LONG).show();
                                //startActivity(FeedActivity.newIntent(MainActivity.this));
                                return true;
                            case R.id.navItemLogout:
                                Toast.makeText(HomeActivity.this, "tem Rate", Toast.LENGTH_LONG).show();
                                //mMainViewModel.logout();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }

    private void showAboutFragment() {
        lockDrawer();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.clRootView, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();
    }
   /* private void showProductFragment() {
        lockDrawer();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.clRootView, ProductFragment.newInstance(), ProductFragment.TAG)
                .commit();
    }*/

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
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            onFragmentDetached(AboutFragment.TAG);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

}

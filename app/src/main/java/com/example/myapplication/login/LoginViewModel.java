package com.example.myapplication.login;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.example.myapplication.base.BaseViewModel;
import com.example.myapplication.remote.RApiClient;
import com.example.myapplication.remote.RApiInterface;
import com.example.myapplication.utils.CommonUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class LoginViewModel extends BaseViewModel<LoginNavigator>{
    @Override
    protected void onCleared() {
        super.onCleared();
    }
    public boolean isEmailAndPasswordValid(String email, String password) {
        // validate email and password
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        if (!CommonUtils.isEmailValid(email)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }
    public void login(String email, String password) {
        setIsLoading(true);
        if(email.equals("1234@gmail.com") && password.equals("1234"))
        {
            setIsLoading(false);
            getNavigator().openMainActivity();
        }else {
            setIsLoading(false);
            getNavigator().invalidUser();
        }
        
    }
    //Method for calling sign in retrofit web service
    private void callRetrofitSignIn() {
       // RApiInterface rApiInterface= RApiClient.getClient(RApiInterface.class,this);
         /*   ApiInterface apiInterface = ApiClientSignInWithInterceptor.getClient().create(ApiInterface.class);
            Call<UserDtoNew> call = apiInterface.signIn(strEmail,
                    strPassword, strFcmToken, "android");
            call.enqueue(new retrofit2.Callback<UserDtoNew>() {
                @Override
                public void onResponse(@NonNull Call<UserDtoNew> call, @NonNull Response<UserDtoNew> response) {
                    try {
                        if (response.body() != null) {
                            utilities.dismissDialog(dialog);
                            UserDtoNew resultDto = response.body();
                            if (resultDto != null) {
                                utilities.dismissDialog(dialog);
                                if (resultDto.getStatus()) {
                                    if (resultDto.getResponse().getUser() != null) {
                                        appSession.setLogin(true);
                                        appSession.setUserNew(resultDto);
                                        Objects.requireNonNull(getActivity()).finishAffinity();
                                        utilities.callActivity(activity,HomeActivity.class);
                                    } else
                                        utilities.dialogOK(getActivity(), getResources().getString(R.string.Whoops), resultDto.getMessage(),
                                                getString(R.string.ok), false);
                                } else
                                    utilities.dialogOK(getActivity(), getResources().getString(R.string.Whoops), resultDto.getMessage(),
                                            getString(R.string.ok), false);
                            } else {
                                utilities.dismissDialog(dialog);
                                utilities.dialogOK(getActivity(), getResources().getString(R.string.Whoops), getResources().getString(R.string.server_error),
                                        getString(R.string.ok), false);
                            }
                        } else {
                            utilities.dismissDialog(dialog);
                            utilities.dialogOK(getActivity(), getResources().getString(R.string.Whoops), getString(R.string.add_to_cart_successfully),
                                    getString(R.string.ok), false);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UserDtoNew> call, Throwable t) {
                    call.cancel();
                    utilities.dismissDialog(dialog);
                    utilities.dialogOK(getActivity(), getResources().getString(R.string.Whoops), getString(R.string.add_to_cart_successfully),
                            getString(R.string.ok), false);
                }
            });
        }*/
    }

    //Method for printing hash key
    public void printHashKey(Context mContext) {
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo("com.webdesky.koupon", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("KeyHash--", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("Exception--", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("Exception--", "printHashKey()", e);
        }
    }
    public void onServerLoginClick() {
        getNavigator().login();
    }
}

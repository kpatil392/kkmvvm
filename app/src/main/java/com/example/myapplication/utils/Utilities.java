package com.example.myapplication.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by Vishwnath on 11/10/19.
 */
public class Utilities implements AppConstants {

    //Declaration of variables
    private ConnectivityManager cm;
    static Context context;
    private static Utilities singleton = null;
    public Integer REQUEST_READ_PHONE_STATE = 0x8;

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private Utilities() {
    }

    /* Static 'instance' method */
    public static Utilities getInstance(Context mContext) {
        context = mContext;
        if (singleton == null)
            singleton = new Utilities();
        return singleton;
    }

    /**
     * Method for checking network availability
     */
    public boolean isNetworkAvailable(String s) {
        try {
            cm = (ConnectivityManager) context
                    .getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            // if no network is available networkInfo will be null
            // otherwise check if we are connected
            if (networkInfo != null && networkInfo.isConnected())
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Check if the connection is fast
     *
     * @param type
     * @param subType
     * @return
     */
    public boolean isConnectionFast(int type, int subType) {
        if (type == ConnectivityManager.TYPE_WIFI) {
            Log.i(getClass().getName(), "Wifi State");
            return true;
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subType) {
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    Log.i(getClass().getName(), "50 - 100 kbps");
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    Log.i(getClass().getName(), "14 - 64 kbps");
                    return false; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    Log.i(getClass().getName(), "50 - 100 kbps");
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    Log.i(getClass().getName(), "400 - 1000 kbps");
                    return true; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    Log.i(getClass().getName(), "600 - 1400 kbps");
                    return true; // ~ 600-1400 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    Log.i(getClass().getName(), " 100 kbps");
                    return false; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    Log.i(getClass().getName(), "2 - 14 Mbps");
                    return true; // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    Log.i(getClass().getName(), "700 - 1700 kbps");
                    return true; // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    Log.i(getClass().getName(), "1 - 23 Mbps");
                    return true; // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    Log.i(getClass().getName(), "400 - 7000 kbps");
                    return true; // ~ 400-7000 kbps
                /*
                 * Above API level 7, make sure to set android:targetSdkVersion
                 * to appropriate level to use these
                 */
                case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                    Log.i(getClass().getName(), "1 - 2 Mbps");
                    return true; // ~ 1-2 Mbps
                case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                    Log.i(getClass().getName(), "5 Mbps");
                    return true; // ~ 5 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                    Log.i(getClass().getName(), "10 - 20 Mbps");
                    return true; // ~ 10-20 Mbps
                case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                    Log.i(getClass().getName(), "25 kbps");
                    return false; // ~25 kbps
                case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                    Log.i(getClass().getName(), "10+ Mbps");
                    return true; // ~ 10+ Mbps
                // Unknown
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                default:
                    return false;
            }
        } else {
            return false;
        }
    }


    /**
     * Method for checking network availability
     */
    public boolean isNetworkAvailable() {
        try {
            cm = (ConnectivityManager) context
                    .getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            // if no network is available networkInfo will be null
            // otherwise check if we are connected
            if (networkInfo != null && networkInfo.isConnected()) {
                if (isConnectionFast(networkInfo.getType(), networkInfo.getSubtype())) {
                    return true;
                } else {
                    dialogOK(context, "", "Your connection is too low", "OK", false);
                    return false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method for getting imei or device id
     */
    public String getIMEIorDeviceId(Activity activity) {
        String deviceId = "";
        if (isEmulator()) return "ANDROID_EMULATOR";
        try {
            TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE},
                        REQUEST_READ_PHONE_STATE);
            } else {
                if (mTelephony.getDeviceId() != null) {
                    deviceId = mTelephony.getDeviceId();
                } else {
                    deviceId = Settings.Secure.getString(
                            context.getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deviceId;
    }

    /**
     * Method for checking emulator
     */
    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT)
                || Build.PRODUCT.equals("sdk")
                || Build.PRODUCT.contains("_sdk")
                || Build.PRODUCT.contains("sdk_");
    }

    /**
     * Method for getting application version code
     */
    public String getAppVersion() {
        String appVersion = "";
        try {
            appVersion = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersion;
    }


    /**
     * Method for validation of ic_email
     */
    public boolean checkEmail(String email) {
        try {
            return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
        } catch (NullPointerException exception) {
            return false;
        }
    }


    /**
     * Method for validation of password
     */
    public boolean checkPassword(String password) {
        try {
            return PASSWORD_PATTERN.matcher(password).matches();
        } catch (NullPointerException exception) {
            return false;
        }
    }


    /**
     * Method for validation of mobile number
     */
    public boolean checkMobile(String mobile) {
        try {
            mobile = mobile.replaceAll("[^0-8]", "");
            if (MOBILE_NUMBER_PATTERN.matcher(mobile).matches())
                return true;
            else
                return false;
        } catch (Exception exception) {
            return false;
        }
    }


    /**
     * Method for getting absolute path
     */
    public String getAbsolutePath(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};

        // Cursor cursor = ((Activity) context).managedQuery(uri, projection,
        // null, null, null);
        Cursor cursor = context.getContentResolver().query(uri, projection,
                null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else return null;
    }

    //Load animation
    Animation slide_down = AnimationUtils.loadAnimation(context,
            R.anim.slide_down);

    Animation slide_up = AnimationUtils.loadAnimation(context,
            R.anim.slide_up);

    /**
     * Method for alerting dialogue
     */
    public void dialogOK(final Context context, String title, String message,
                         String btnText, final boolean isFinish) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("");
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton(btnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (isFinish)
                    ((Activity) context).finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        alertDialog.show();
    }

    /**
     * Method for hiding device keyboard
     */
    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
//    public static void hideKeyboard() {
//        InputMethodManager inputManager = (InputMethodManager) context
//                .getSystemService(Context.INPUT_METHOD_SERVICE);
//
//        // check if no view has focus:
//        View v = ((Activity) context).getCurrentFocus();
//        if (v == null)
//            return;
//
//        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
//    }


    //For Merging

    /**
     * This method used to create new file if not exist .
     */
    public File getNewFile(String directoryName, String imageName) {
        String root = Environment.getExternalStorageDirectory()
                + directoryName;
        File file;
        if (isSDCARDMounted()) {
            new File(root).mkdirs();
            file = new File(root, imageName);
        } else {
            file = new File(context.getFilesDir(), imageName);
        }
        return file;
    }

    public boolean isSDCARDMounted() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED))
            return true;
        return false;
    }

    public static Bitmap decodeFile(File f, int REQUIRED_WIDTH,
                                    int REQUIRED_HEIGHT) {
        try {
            ExifInterface exif = new ExifInterface(f.getPath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            // Log.i("exif.getAttribute(ExifInterface.TAG_ORIENTATION)",
            // exif.getAttribute(ExifInterface.TAG_ORIENTATION));
            int angle = 0;

            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                angle = 90;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                angle = 180;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                angle = 270;
            }
            // Log.i("path & orientation & angle", f.getPath() + " & "
            // + orientation + " & " + angle);
            Matrix mat = new Matrix();
            mat.postRotate(angle);
            // decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);
            // Find the correct scale value. It should be the power of 2.
            int REQUIRED_SIZE = 100; // 70
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            // Log.i("W*H Before.....................", scale + " " + width_tmp
            // + "*" + height_tmp);
            if (width_tmp > height_tmp) {
                REQUIRED_SIZE = REQUIRED_HEIGHT;
                REQUIRED_HEIGHT = REQUIRED_WIDTH;
                REQUIRED_WIDTH = REQUIRED_SIZE;
            }
            while (true) {
                if (width_tmp / 2 < REQUIRED_WIDTH
                        && height_tmp / 2 < REQUIRED_HEIGHT)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            // Log.i("W*H After.....................", scale + " " + width_tmp
            // + "*" + height_tmp);
            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            o2.inPurgeable = true;
            // return BitmapFactory.decodeStream(new FileInputStream(f), null,
            // o2);
            Bitmap correctBmp = BitmapFactory.decodeStream(new FileInputStream(
                    f), null, o2);
            correctBmp = Bitmap.createBitmap(correctBmp, 0, 0,
                    correctBmp.getWidth(), correctBmp.getHeight(), mat, true);
            return correctBmp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getFilePath(Bitmap bitmap, Context context, String path) {
        //  File cacheDir;
        File file;

        try {
//            if (android.os.Environment.getExternalStorageState().equals(
//                    android.os.Environment.MEDIA_MOUNTED))
//                cacheDir = new File(
//                        android.os.Environment.getExternalStorageDirectory(),
//                        "NIGHTLIVE/Media/Images");
//            else
//                cacheDir = context.getCacheDir();
//            if (!cacheDir.exists())
//                cacheDir.mkdirs();

            if (bitmap != null) {
                String FILE_NAME = "dnd_" + new Date().getTime() + ".jpg";
                file = new File(path);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                FileOutputStream fo;

                fo = new FileOutputStream(file);
                fo.write(bytes.toByteArray());
                fo.close();

                return file.getAbsolutePath();
            }

        } catch (Exception e1) {
            e1.printStackTrace();
            Log.v("", "getMessage " + e1.getMessage());

        } catch (Error e1) {
            e1.printStackTrace();
            Log.v("", "getMessage " + e1.getMessage());
        }
        /*
         * if (myDir.isDirectory()) { String[] children = myDir.list(); for (int
         * i = 0; i < children.length; i++) { new File(myDir,
         * children[i]).delete(); } }
         */
        return "";
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    //For validating url
    public boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url.toLowerCase());
        if (m.matches())
            return true;
        else
            return false;
    }

    //Method for opening fragment with back stack
    public void showFragmentWithBackStack(Fragment targetFragment, String className,
                                          FragmentTransaction ft) {
//            ft.setCustomAnimations(
//                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
//                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                ft.replace(R.id.container, targetFragment, className)
                .addToBackStack(className)
                .commit();
    }


    //Method for opening fragment without back stack
    public void showFragmentWithoutBackStack(Fragment targetFragment, String className,
                                             FragmentTransaction ft) {
//                ft.setCustomAnimations(
//                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
//                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                ft.replace(R.id.container, targetFragment, className)
                .commit();
    }

    //Method for opening fragment without back stack
    public void showFragmentWithoutBackStackAndAnimation(Fragment targetFragment, String className,
                                                         FragmentTransaction ft) {
        ft.replace(R.id.container, targetFragment, className)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    //Method for opening fragment with back stack and without animation
    public void showFragmentWithBackStackAndWithoutAnimation(Fragment targetFragment, String className,
                                                         FragmentTransaction ft) {
        ft.replace(R.id.container, targetFragment, className)
                .addToBackStack(className)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    //Method for opening fragment with back stack and slide up animation
    public void showFragmentWithBackStackAndSlideUpAnimation(Fragment targetFragment, String className,
                                                             FragmentTransaction ft) {
        ft.setCustomAnimations(R.anim.slide_up,0)
                .replace(R.id.container, targetFragment, className)
                .addToBackStack(className)
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }



    //Method for opening fragment without back stack
    public void showFragmentClearBackStackLogin(Fragment targetFragment, String className,
                                                FragmentTransaction ft) {
        ft.setCustomAnimations(
                R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                .replace(R.id.container, targetFragment, className)
                .commit();
    }

    /**
     * method to Hide keyboard
     *
     * @param view
     */
    public void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    //Method for showing toast
//    public static void showToast(String message) {
//        new Handler().post(() -> {
//            Toast toast = Toast.makeText(SocialApplication.getInstance(), message, Toast.LENGTH_LONG);
//            toast.setGravity(Gravity.CENTER, 0, 0);
//            toast.show();
//        });
//    }
//
//    public void showDialog(final AppCompatDialog dialog) {
//        if (dialog != null && !dialog.isShowing())
//            new Handler().post(dialog::show);
//    }
//
//    public void dismissDialog(final AppCompatDialog dialog) {
//        if (dialog != null && dialog.isShowing())
//            new Handler().post(dialog::dismiss);
//    }

    public AppCompatDialog initDialog(Context activity) {
        AppCompatDialog dialog = new AppCompatDialog(activity);
        dialog.setContentView(R.layout.dialog_progress);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        return dialog;
    }


    public void callActivityWithFinish(Activity activity, Class activityClass) {
        Intent intent = new Intent(activity, activityClass);
        activity.startActivity(intent);
        activity.finish();
    }

    public void callActivityWithFinishBundle(Activity activity, Class activityClass, Bundle bundle) {
        Intent intent = new Intent(activity, activityClass);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.finish();
    }

    public void callActivity(Activity activity, Class activityClass) {
        Intent intent = new Intent(activity, activityClass);
        activity.startActivity(intent);
    }

    public void callActivityWithBundle(Activity activity, Class activityClass, Bundle bundle) {
        Intent intent = new Intent(activity, activityClass);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


    /*public void showDialog(final AppCompatDialog dialog) {
        if (dialog != null && !dialog.isShowing())
            new Handler().post(dialog::show);
    }

    public void dismissDialog(final AppCompatDialog dialog) {
        if (dialog != null && dialog.isShowing())
            new Handler().post(dialog::dismiss);
    }*/
    //Method for setting cart counting
   /* public void setCartAction(AppSession appSession,Menu menu,
                              OnItemClickListener.OnItemClickCallback onItemClickCallback)
    {
        MenuItem item1 = menu.findItem(R.id.action_cart);
        MenuItemCompat.setActionView(item1, R.layout.custom_menu);
        RelativeLayout notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);
        TextView textView = (TextView) notificationCount1.findViewById(R.id.hotlist_hot);
        ImageView imageView = (ImageView) notificationCount1.findViewById(R.id.hotlist_bell);

        notificationCount1.setOnClickListener(new OnItemClickListener(0, onItemClickCallback));

        if(appSession.isLogin()) {
            if (appSession.getUserNew().getResponse().getUser().getCartTotal().equals("0")) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setText(appSession.getUserNew().getResponse().getUser().getCartTotal());
            }
        }
        else textView.setVisibility(View.GONE);
    }*/


}





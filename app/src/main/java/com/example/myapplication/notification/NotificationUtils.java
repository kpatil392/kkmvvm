package com.example.myapplication.notification;


import android.annotation.TargetApi;
import android.app.Notification;
        import android.app.NotificationChannel;
        import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
        import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@RequiresApi(api = Build.VERSION_CODES.O)
public class NotificationUtils extends ContextWrapper {
    private NotificationManager mManager;
    public static final String ANDROID_CHANNEL_ID = "com.chikeandroid.tutsplustalerts.ANDROID";
    public static final String IOS_CHANNEL_ID = "com.chikeandroid.tutsplustalerts.IOS";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";
    public static final String IOS_CHANNEL_NAME = "IOS CHANNEL";


    int SUMMARY_ID = 0;
    String GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL";

    public NotificationUtils(Context mContext) {
        super(mContext);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            createChannels();
        }
    }
    public void createChannels() {
        // create android channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                    ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            // Sets whether notifications posted to this channel should display notification lights
            androidChannel.enableLights(true);
            // Sets whether notification posted to this channel should vibrate.
            androidChannel.enableVibration(true);
            // Sets the notification light color for notifications posted to this channel
            androidChannel.setLightColor(Color.GREEN);
            // Sets whether notifications posted to this channel appear on the lockscreen or not
            androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(androidChannel);

            // create ios channel
            NotificationChannel iosChannel = new NotificationChannel(IOS_CHANNEL_ID,
                    IOS_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            iosChannel.enableLights(true);
            iosChannel.enableVibration(true);
            iosChannel.setLightColor(Color.GRAY);
            iosChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            getManager().createNotificationChannel(iosChannel);
        }
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    public void getHeadWithLargeIcon(String title, String body,Context mContext) {
        NotificationCompat.Builder nBuilder= new NotificationCompat.Builder(mContext, ANDROID_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.other))
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))//getResources().getString(R.string.lorem_ipsum)))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setGroup(GROUP_KEY_WORK_EMAIL)
                //set this notification as the summary for the group
                .setGroupSummary(true)
                .setAutoCancel(true); //allow auto cancel when pressed
        mManager.notify(1001, nBuilder.build());// .

    }
    public void getSimpleNotification(String title, String body,Context mContext) {
        NotificationCompat.Builder nBuilder= new NotificationCompat.Builder(mContext, ANDROID_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setGroup(GROUP_KEY_WORK_EMAIL)
                //set this notification as the summary for the group
                .setGroupSummary(true)
                .setAutoCancel(true); //allow auto cancel when pressed.
        mManager.notify(1001, nBuilder.build());
    } 
    public void getLargeTextNotification(String title, String body,Context mContext) {
        NotificationCompat.Builder nBuilder= new NotificationCompat.Builder(mContext, ANDROID_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(body))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setGroup(GROUP_KEY_WORK_EMAIL)
                //set this notification as the summary for the group
                .setGroupSummary(true)
                .setAutoCancel(true); //allow auto cancel when pressed.
        mManager.notify(1001, nBuilder.build());
    }
    public void getLargeIconWithImageNotification(String title, String body,Context mContext) {
        Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.other);
        Intent intent = new Intent(mContext, LoginActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

        NotificationCompat.Builder nBuilder= new NotificationCompat.Builder(mContext, ANDROID_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.other))
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(pic)
                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY_WORK_EMAIL)
                //set this notification as the summary for the group
                .setGroupSummary(true)
                .setContentIntent(pIntent); //allow auto cancel when pressed.
        mManager.notify(143, nBuilder.build());
    }


    public void createProgressNotification (Context mContext) {
          
        // used to update the progress notification
        final int progresID = new Random().nextInt(1000);

        // building the notification
        final NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(mContext,ANDROID_CHANNEL_ID)
                .setSmallIcon(R.drawable.bg_design)
                .setContentTitle("Progres notification")
                .setContentText("Now waiting")
                .setTicker("Progress notification created")
                .setUsesChronometer(true)
                .setGroup(GROUP_KEY_WORK_EMAIL)
                //set this notification as the summary for the group
                .setGroupSummary(true)
                .setProgress(100, 0, true);



        AsyncTask<Integer, Integer, Integer> downloadTask = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected void onPreExecute () {
                super.onPreExecute();
                mManager.notify(progresID, nBuilder.build());
            }

            @Override
            protected Integer doInBackground (Integer... params) {
                try {
                    // Sleeps 2 seconds to show the undeterminated progress
                    Thread.sleep(5000);

                    // update the progress
                    for (int i = 0; i < 101; i+=5) {
                        nBuilder
                                .setContentTitle("Progress running...")
                                .setContentText("Now running...")
                                .setProgress(100, i, false)
                                .setSmallIcon(R.drawable.bg_design)
                                .setContentInfo(i + " %");

                        // use the same id for update instead created another one
                        mManager.notify(progresID, nBuilder.build());
                        Thread.sleep(500);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }


            @Override
            protected void onPostExecute (Integer integer) {
                super.onPostExecute(integer);

                nBuilder.setContentText("Progress finished :D")
                        .setContentTitle("Progress finished !!")
                        .setTicker("Progress finished !!!")
                        .setSmallIcon(R.drawable.button_bg)
                        .setUsesChronometer(false);

                mManager.notify(progresID, nBuilder.build());
            }
        };

        // Executes the progress task
        downloadTask.execute();
    }

    public void createButtonNotification (Context context) {
        //if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // Prepare intent which is triggered if the  notification button is pressed
            Intent intent = new Intent(context, LoginActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Building the notifcation
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context,ANDROID_CHANNEL_ID)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setSmallIcon(R.drawable.bg_circle) // notification icon
                    .setContentTitle("Button notification") // notification title
                    .setContentText("Expand to show the buttons...") // content text
                    .setTicker("Showing button notification") // status bar message
                    .addAction(R.drawable.ic_home_black_24dp, "Accept", pIntent) // accept notification button
                    .addAction(R.drawable.ic_notifications_black_24dp, "Cancel", pIntent)
                    .setAutoCancel(true)
                    .setGroup(GROUP_KEY_WORK_EMAIL)
                    //set this notification as the summary for the group
                    .setGroupSummary(true);// cancel notification button

            mManager.notify(1001, nBuilder.build());

       
    }
    //////
    public void getLargeTextSetActionNotification(final String title, final String body,String url,final Context mContext) {
      //https://upload.wikimedia.org/wikipedia/en/thumb/6/63/IMG_%28business%29.svg/1200px-IMG_%28business%29.svg.png
        Intent intent = new Intent(mContext, LoginActivity.class);
        final PendingIntent pIntent = PendingIntent.getActivity(mContext, 0, intent, 0);

        Glide.with(mContext).asBitmap().load(url).apply(new RequestOptions().override(400, 400)).into(new CustomTarget<Bitmap>() {
            
            @Override
            public void onResourceReady(@NonNull Bitmap bmp, @Nullable Transition<? super Bitmap> transition) {
                NotificationCompat.Builder nb= new NotificationCompat.Builder(mContext, ANDROID_CHANNEL_ID)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setSmallIcon(android.R.drawable.stat_notify_more)
                        .setAutoCancel(true)
                        .setLargeIcon(bmp)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                 .bigPicture(bmp)
                                 .bigLargeIcon(null))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true)
                        .setContentIntent(pIntent); //allow auto cancel when pressed.
                mManager.notify(144, nb.build());
            }
            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });
        
    }
    
    public class generatePictureStyleNotification extends AsyncTask<String, Void, Bitmap> {

        private Context mContext;
        private String title, message, imageUrl;

        public generatePictureStyleNotification(Context context, String title, String message, String imageUrl) {
            super();
            this.mContext = context;
            this.title = title;
            this.message = message;
            this.imageUrl = imageUrl;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            InputStream in;
            try {
                URL url = new URL(this.imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);

          /*  Intent intent = new Intent(mContext, MyOpenableActivity.class);
            intent.putExtra("key", "value");
            PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 100, intent, PendingIntent.FLAG_ONE_SHOT);
*/
            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notif = new Notification.Builder(mContext)
                    //.setContentIntent(pendingIntent)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(result)
                    .setStyle(new Notification.BigPictureStyle().bigPicture(result))
                    .build();
            notif.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(1, notif);
        }
    }
    public void GroupNotification(String title,String body,Context mContext)
    {
        int SUMMARY_ID = 0;
        String GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL";

        Notification newMessageNotification1 =
                new NotificationCompat.Builder(mContext, ANDROID_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setGroup(GROUP_KEY_WORK_EMAIL)
                        .build();

        Notification newMessageNotification2 =
                new NotificationCompat.Builder(mContext, ANDROID_CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setGroup(GROUP_KEY_WORK_EMAIL)
                        .build();

        Notification summaryNotification =
                new NotificationCompat.Builder(mContext,ANDROID_CHANNEL_ID)
                        .setContentTitle(title)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        //set content text to support devices running API level < 24
                        .setContentText("Two new messages")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        //build summary info into InboxStyle template
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine("Alex Faarborg  Check this out")
                                .addLine("Jeff Chang    Launch Party")
                                .setBigContentTitle("2 new messages")
                                .setSummaryText("janedoe@example.com"))
                        //specify which group this notification belongs to
                        .setGroup(GROUP_KEY_WORK_EMAIL)
                        //set this notification as the summary for the group
                        .setGroupSummary(true)
                        .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(11, newMessageNotification1);
        notificationManager.notify(12, newMessageNotification2);
        notificationManager.notify(SUMMARY_ID, summaryNotification);
    }
}
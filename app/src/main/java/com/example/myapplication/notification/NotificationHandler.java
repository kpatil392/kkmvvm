package com.example.myapplication.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.example.myapplication.R;
import com.example.myapplication.login.LoginActivity;

import java.util.Random;

public class NotificationHandler {
    // Notification handler singleton
    private static NotificationHandler nHandler;
    private static NotificationManager mNotificationManager;


    private NotificationHandler () {}


    /**
     * Singleton pattern implementation
     * @return
     */
    public static  NotificationHandler getInstance(Context context) {
        if(nHandler == null) {
            nHandler = new NotificationHandler();
            mNotificationManager =(NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            Log.i("Chennel",Build.VERSION.SDK_INT+"--"+Build.VERSION_CODES.O);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.i("Chennel",String.valueOf(R.string.noti_two_desc));
                createNotificationChannel(context);
            }
        }

        return nHandler;
    }

    public static void createNotificationChannel(Context mContext) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = mContext.getString(R.string.noti_two);
            String description = mContext.getString(R.string.noti_two_desc);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("121", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Shows a simple notification
     * @param context aplication context
     */
    public void createSimpleNotification(Context context) {
        // Creates an explicit intent for an Activity
        Intent resultIntent = new Intent(context, LoginActivity.class);

        // Creating a artifical activity stack for the notification activity
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(LoginActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        // Pending intent to the notification manager
        PendingIntent resultPending = stackBuilder
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, String.valueOf(R.string.noti_two))
                .setSmallIcon(R.drawable.ic_home_black_24dp)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setContentIntent(resultPending)// 
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        // Building the notification
        /*NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,String.valueOf(R.string.noti_two))
                .setSmallIcon(R.drawable.bg_circle) // notification icon
                .setContentTitle("I'm a simple notification") // main title of the notification
                .setContentText("I'm the text of the simple notification") // notification text
                .setContentIntent(resultPending)// notification intent
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
*/

      /*  Notification noti = new Notification.Builder(context, "Login")
                .setSmallIcon(R.mipmap.ic_launcher)
                //.setWhen(System.currentTimeMillis())  //When the event occurred, now, since noti are stored by time.
                .setChannelId(n)
                .setContentTitle("Service")   //Title message top row.
                .setContentText(message)  //message when looking at the notification, second row
                .setAutoCancel(true)   //allow auto cancel when pressed.
                .build();  //finally build and return a Notification.
*/
        // mId allows you to update the notification later on.
        mNotificationManager.notify(10, mBuilder.build());
    }


    public void createExpandableNotification (Context context) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // Building the expandable content
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            String lorem = context.getResources().getString(R.string.activity_title_about_us);
            String [] content = lorem.split("\\.");

            inboxStyle.setBigContentTitle("This is a big title");
            for (String line : content) {
                inboxStyle.addLine(line);
            }

            // Building the notification
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.bg_circle) // notification icon
                    .setContentTitle("Expandable notification") // title of notification
                    .setContentText("This is an example of an expandable notification") // text inside the notification
                    .setStyle(inboxStyle); // adds the expandable content to the notification

            mNotificationManager.notify(11, nBuilder.build());

        } else {
            Toast.makeText(context, "Can't show", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Show a determinate and undeterminate progress notification
     * @param context, activity context
     */
    public void createProgressNotification (final Context context) {

        // used to update the progress notification
        final int progresID = new Random().nextInt(1000);

        // building the notification
        final NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.bg_design)
                .setContentTitle("Progres notification")
                .setContentText("Now waiting")
                .setTicker("Progress notification created")
                .setUsesChronometer(true)
                .setProgress(100, 0, true);



        AsyncTask<Integer, Integer, Integer> downloadTask = new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected void onPreExecute () {
                super.onPreExecute();
                mNotificationManager.notify(progresID, nBuilder.build());
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
                        mNotificationManager.notify(progresID, nBuilder.build());
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

                mNotificationManager.notify(progresID, nBuilder.build());
            }
        };

        // Executes the progress task
        downloadTask.execute();
    }


    public void createButtonNotification (Context context) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // Prepare intent which is triggered if the  notification button is pressed
            Intent intent = new Intent(context, LoginActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Building the notifcation
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.bg_circle) // notification icon
                    .setContentTitle("Button notification") // notification title
                    .setContentText("Expand to show the buttons...") // content text
                    .setTicker("Showing button notification") // status bar message
                    .addAction(R.drawable.bg_circle, "Accept", pIntent) // accept notification button
                    .addAction(R.drawable.button_bg, "Cancel", pIntent); // cancel notification button

            mNotificationManager.notify(1001, nBuilder.build());

        } else {
            Toast.makeText(context, "You need a higher version", Toast.LENGTH_LONG).show();
        }
    }
}

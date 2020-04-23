/*
package com.example.myapplication.zzzzetc;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNotificationActions, btnHeadsUpNotification, btnBigTextStyle, btnBigPictureStyle,
            btnInboxStyle, btnMessageStyle;

    NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clearNotification();

        btnNotificationActions = (Button) findViewById(R.id.btnNotificationActions);
        btnHeadsUpNotification = (Button) findViewById(R.id.btnHeadsUp);
        btnBigTextStyle = (Button) findViewById(R.id.btnBigTextStyle);
        btnBigPictureStyle = (Button) findViewById(R.id.btnBigPictureStyle);
        btnInboxStyle = (Button) findViewById(R.id.btnInboxStyle);
        btnMessageStyle = (Button) findViewById(R.id.btnMessageStyle);
        btnNotificationActions.setOnClickListener(this);
        btnHeadsUpNotification.setOnClickListener(this);
        btnBigTextStyle.setOnClickListener(this);
        btnBigPictureStyle.setOnClickListener(this);
        btnInboxStyle.setOnClickListener(this);
        btnMessageStyle.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNotificationActions:
                notificationActions();
                break;
            case R.id.btnHeadsUp:
                headsUpNotification();
                break;
            case R.id.btnBigTextStyle:
                bigTextStyleNotification();
                break;
            case R.id.btnBigPictureStyle:
                bigPictureStyleNotification();
                break;
            case R.id.btnInboxStyle:
                inboxStyleNotification();
                break;
            case R.id.btnMessageStyle:
                messageStyleNotification();
                break;

        }
    }

    private void notificationActions() {

        int NOTIFICATION_ID = 1;


        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.jd);
        builder.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.jd));
        builder.setContentTitle("Notification Actions");
        builder.setContentText("Tap View to launch our website");
        builder.setAutoCancel(true);
        PendingIntent launchIntent = getLaunchIntent(NOTIFICATION_ID, getBaseContext());

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.journaldev.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Intent buttonIntent = new Intent(getBaseContext(), NotificationReceiver.class);
        buttonIntent.putExtra("notificationId", NOTIFICATION_ID);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);

        builder.setContentIntent(launchIntent);
        builder.addAction(android.R.drawable.ic_menu_view, "VIEW", pendingIntent);
        builder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissIntent);

        buildNotification(NOTIFICATION_ID);
    }

    public PendingIntent getLaunchIntent(int notificationId, Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("notificationId", notificationId);
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }


    private void clearNotification() {
        int notificationId = getIntent().getIntExtra("notificationId", 0);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(notificationId);
    }

    private void headsUpNotification() {

        int NOTIFICATION_ID = 1;
        builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.jd)
                        .setContentTitle("Heads Up Notification")
                        .setContentText("View the latest Swift Tutorial")
                        .setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.journaldev.com/15126/swift-function"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Intent buttonIntent = new Intent(getBaseContext(), NotificationReceiver.class);
        buttonIntent.putExtra("notificationId", NOTIFICATION_ID);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);

        builder.addAction(android.R.drawable.ic_menu_view, "VIEW", pendingIntent);
        builder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissIntent);

        buildNotification(NOTIFICATION_ID);
    }

    private void bigTextStyleNotification() {
        int NOTIFICATION_ID = 1;

        PendingIntent launchIntent = getLaunchIntent(NOTIFICATION_ID, getBaseContext());
        Intent buttonIntent = new Intent(getBaseContext(), NotificationReceiver.class);
        buttonIntent.putExtra("notificationId", NOTIFICATION_ID);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);


        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.jd);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.jd));
        builder.setContentTitle("Big Text Style");
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getResources().getString(R.string.lorem_ipsum)));
        builder.setAutoCancel(true);
        builder.setContentIntent(launchIntent);
        builder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissIntent);
        builder.addAction(android.R.drawable.ic_menu_send, "OPEN APP", launchIntent);

        buildNotification(NOTIFICATION_ID);
    }

    private void bigPictureStyleNotification() {
        int NOTIFICATION_ID = 1;

        Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.bg);


        Intent buttonIntent = new Intent(getBaseContext(), NotificationReceiver.class);
        buttonIntent.putExtra("notificationId", NOTIFICATION_ID);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);
        PendingIntent launchIntent = getLaunchIntent(NOTIFICATION_ID, getBaseContext());


        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.jd);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.jd));
        builder.setContentTitle("Big Picture Style");
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(pic));
        builder.setAutoCancel(true);
        builder.setContentIntent(launchIntent);
        builder.addAction(android.R.drawable.ic_delete, "DISMISS", dismissIntent);

        buildNotification(NOTIFICATION_ID);
    }

    private void inboxStyleNotification() {
        int NOTIFICATION_ID = 1;

        PendingIntent launchIntent = getLaunchIntent(NOTIFICATION_ID, getBaseContext());
        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.jd);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.jd));
        builder.setContentTitle("Messages");
        builder.setStyle(new NotificationCompat.InboxStyle().addLine("Hello").addLine("Are you there?").addLine("How's your day?").setBigContentTitle("3 New Messages for you").setSummaryText("Inbox"));
        builder.setAutoCancel(true);
        builder.setContentIntent(launchIntent);

        buildNotification(NOTIFICATION_ID);
    }

    private void messageStyleNotification() {
        int NOTIFICATION_ID = 1;

        PendingIntent launchIntent = getLaunchIntent(NOTIFICATION_ID, getBaseContext());
        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.jd);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.jd));
        builder.setContentTitle("Messages");
        builder.setStyle(new NotificationCompat.MessagingStyle("Teacher").setConversationTitle("Q&A Group")
                .addMessage("This type of notification was introduced in Android N. Right?", 0, "Student 1")
                .addMessage("Yes", 0, null)
                .addMessage("The constructor is passed with the name of the current user. Right?", 0, "Student 2")
                .addMessage("True", 0, null));
        builder.setAutoCancel(true);
        builder.setContentIntent(launchIntent);

        buildNotification(NOTIFICATION_ID);
    }

    private void buildNotification(int NOTIFICATION_ID) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }


}
*/

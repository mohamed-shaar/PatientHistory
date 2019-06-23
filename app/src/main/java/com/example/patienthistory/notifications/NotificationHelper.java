package com.example.patienthistory.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import com.example.patienthistory.R;

/**
 *  This class sets up notification channels
 */
public class NotificationHelper extends ContextWrapper {

    private NotificationManager mNotificationManager;

    public NotificationHelper(Context base) {
        super(base);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel medicineChannel = new NotificationChannel(
                    String.valueOf(R.string.medicine_channel),
                    "wolo",
                    NotificationManager.IMPORTANCE_HIGH);

            getNotificationManager().createNotificationChannel(medicineChannel);

            NotificationChannel chatChannel = new NotificationChannel(
                    getString(R.string.chat_channel),
                    "wolo",
                    NotificationManager.IMPORTANCE_HIGH);

            getNotificationManager().createNotificationChannel(chatChannel);
        }
    }

    private NotificationManager getNotificationManager() {
        if (mNotificationManager == null) {
            mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }

}

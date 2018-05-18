package com.project.swagkennels;

import android.os.Bundle;
import android.util.Log;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public final String TAG = "Interceptor";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            scheduleJob(
                    remoteMessage.getData().get("message") != null ? remoteMessage.getData().get("message") : "",
                    remoteMessage.getData().get("title") != null ? remoteMessage.getData().get("title") : getString(R.string.app_name),
                    remoteMessage.getData().get("type") != null ? remoteMessage.getData().get("type") : ""
            );
        }

    }

    private void scheduleJob(String message, String title, String type) {
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        bundle.putString("title", title);
        bundle.putString("type", type);
        Job myJob = dispatcher.newJobBuilder()
                .setService(NotificationsJobService.class)
                .setExtras(bundle)
                .setTag("my-job-tag")
                .build();
        dispatcher.schedule(myJob);
    }

}

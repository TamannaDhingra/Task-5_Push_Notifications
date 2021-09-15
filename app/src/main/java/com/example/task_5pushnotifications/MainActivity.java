
package com.example.task_5pushnotifications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {


    private NotificationManagerCompat notificationManagerCompat;
    EditText editTexttitle,editTextmsg;
    public static final String  CHANNEL_1_ID="channel1";
    public static final String ChANNEL_2_ID="channel2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat=NotificationManagerCompat.from(this);
        editTexttitle=findViewById(R.id.ettitle);
        editTextmsg=findViewById(R.id.etmessage);

//send notification through firebase
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                    }
                });
    }

    //send notification through button click
    public void sendOnChannel1(View view) {
        String title=editTexttitle.getText().toString();
        String msg=editTextmsg.getText().toString();


        Notification notification=new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1,notification);

    }

    public void sendOnChannel2(View view) {
        String title=editTexttitle.getText().toString();
        String msg=editTextmsg.getText().toString();


        Notification notification=new NotificationCompat.Builder(this,ChANNEL_2_ID)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManagerCompat.notify(2,notification);

    }
}
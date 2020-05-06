package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);//获取点击按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);//获取NotificationManager实例
                NotificationCompat.Builder builder;//创建通知对象
                if (Build.VERSION.SDK_INT >= 26) {//判断Android的版本
                    NotificationChannel channel = new NotificationChannel(String.valueOf(1), "name",
                            NotificationManager.IMPORTANCE_HIGH);  //当Android版本大于等于8时，创建通知渠道（Notification Channels）
                    manager.createNotificationChannel(channel);
                    builder = new NotificationCompat.Builder(MainActivity.this,String.valueOf(1));//获取
                }else {
                    builder = new NotificationCompat.Builder(MainActivity.this);//当版本低于8时使用
                }
                        builder.setContentTitle("牛皮哄哄")//通知标题
                        .setContentText("精神小伙") //通知内容
                        .setWhen(System.currentTimeMillis()) //指定通知被创建的时间
                        .setSmallIcon(R.mipmap.ic_launcher) //使用小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)); //使用大图片
                Notification notification = builder.build();
                manager.notify(1,notification);
            }
        });
    }
}

package com.wantchu.wantchu_server2.fcmtest;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.wantchu.wantchu_server2.business.ObjectMaker;
import com.wantchu.wantchu_server2.vo.PrintMarketingInfoVo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class FcmUtil {

    public void send_FCM(String tokenId, String title, String content, org.json.simple.JSONObject jsonObject) throws IOException, FirebaseMessagingException {
            InputStream inputStream = new ClassPathResource("baro-69065-firebase-adminsdk-78yyx-353a272fae.json").getInputStream();
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("https://baro-69065.firebaseio.com")
                    .build();

            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
             Notification notification = new Notification(title, content);
            String registrationToken = tokenId;
            Message message = Message.builder()
                    .setAndroidConfig(AndroidConfig.builder()
                        .setTtl(3600 * 1000)
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .build())
                    .setApnsConfig(ApnsConfig.builder()
                            .setAps(Aps.builder().setSound("default").build())
                            .build())
                    .setNotification(notification)
                    .putData("title", title)
                    .putData("body", content)
                    .setToken(registrationToken)
                    .build();


            String response = FirebaseMessaging.getInstance().send(message);
            jsonObject.put("response", response);
    }
    public void send_FCM_Marketing(List<PrintMarketingInfoVo> device_tokens, String title, String content, org.json.simple.JSONObject jsonObject) throws IOException, FirebaseMessagingException {
        InputStream inputStream = new ClassPathResource("baro-69065-firebase-adminsdk-78yyx-353a272fae.json").getInputStream();
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .setDatabaseUrl("https://baro-69065.firebaseio.com")
                .build();

        if(FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
        for(PrintMarketingInfoVo data : device_tokens) {
            Notification notification = new Notification(title, content);
            Message message = Message.builder()
                    .setAndroidConfig(AndroidConfig.builder()
                            .setTtl(3600 * 1000)
                            .setPriority(AndroidConfig.Priority.HIGH)
                            .build())
                    .setApnsConfig(ApnsConfig.builder()
                            .setAps(Aps.builder().setSound("default").build())
                            .build())
                    .setNotification(notification)
                    .putData("title", title)
                    .putData("body", content)
                    .setToken(data.getDevice_token())
                    .build();


            String response = FirebaseMessaging.getInstance().send(message);
        }
    }

    public void send_owner_FCM(String tokenId, String content, org.json.simple.JSONObject jsonObject) throws IOException, FirebaseMessagingException {
        InputStream inputStream = new ClassPathResource("baro-69065-firebase-adminsdk-78yyx-353a272fae.json").getInputStream();
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .setDatabaseUrl("https://baro-69065.firebaseio.com")
                .build();

        if(FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        String registrationToken = tokenId;

        Message message = Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                        .setTtl(3600 * 1000)
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .build())
                .putData("title", "주문이 들어왔습니다")
                .putData("body", content)
                .setToken(registrationToken)
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        jsonObject.put("response", response);
    }
}

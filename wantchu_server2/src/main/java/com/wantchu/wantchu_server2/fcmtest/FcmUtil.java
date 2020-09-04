package com.wantchu.wantchu_server2.fcmtest;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class FcmUtil {

    public void send_FCM(String tokenId, String title, String content, org.json.simple.JSONObject jsonObject) throws IOException, FirebaseMessagingException {
            InputStream inputStream = new ClassPathResource("/fcm/adminSDK.json").getInputStream();
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(inputStream))
                    .setDatabaseUrl("https://wantchu-458d8.firebaseio.com")
                    .build();

            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            String registrationToken = tokenId;

            Message message = Message.builder()
                    .setAndroidConfig(AndroidConfig.builder()
                        .setTtl(3600 * 1000)
                        .setPriority(AndroidConfig.Priority.NORMAL)
                        .build())
                    .putData("title", title)
                    .putData("body", content)
                    .setToken(registrationToken)
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            jsonObject.put("response", response);
    }
}

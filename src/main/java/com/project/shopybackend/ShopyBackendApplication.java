package com.project.shopybackend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ShopyBackendApplication {
    public static void main(String[] args){
        SpringApplication.run(ShopyBackendApplication.class, args);
    }

    @Bean
    public Firestore firestore() throws Exception {

        InputStream serviceAccount = new FileInputStream("src/main/resources/shopy-f9053-383b51f1b92d.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        return FirestoreClient.getFirestore();
    }
}

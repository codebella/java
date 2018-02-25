package org.codebella.sample.java.fileupload;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class UploadJMS {

    @JmsListener(destination = "${app.jms.file.notification.destination}", containerFactory = "")
    public void getMessage(String data) {
        System.out.println("Received: " + data);
    }
}

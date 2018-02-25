package org.codebella.sample.java.fileupload;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class UploadController {
    private final static String UPLOAD_DIR = "c:/temp/";

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${app.jms.file.notification.destination}")
    private String jmsNotificationDestination;

    @GetMapping("/name")
    public String getName(@RequestParam(value = "name", defaultValue = "Sample Doc Uploader") String name) {
        return "You are @: " + name;
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Encountered Failure";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
            Files.write(path, bytes);
            jmsTemplate.convertAndSend(jmsNotificationDestination, new String(bytes));
        } catch (Exception e) {
            System.out.println("Exception encountered: " + e.getMessage());
        }

        return "Successfully Uploaded";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}

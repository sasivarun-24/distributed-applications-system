package com.example.demo.email;

import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
public class EmailService {
    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());

    public void sendEmail(String userId) {
        LOGGER.info("Sending confirmation email to user: " + userId);
    }
}

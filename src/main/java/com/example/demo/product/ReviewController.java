package com.example.demo.product;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ReviewController {

    @MessageMapping("/review")
    @SendTo("/topic/reviews")
    public Review handleReview(Review review) {
        if (review.getDate() == null) {
            review.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return review;
    }
}

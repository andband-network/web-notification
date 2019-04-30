package com.andband.notification.web;

import com.andband.notification.persistence.EmailContent;
import com.andband.notification.persistence.EmailContentRepository;
import com.andband.notification.persistence.EmailType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private EmailContentRepository emailContentRepository;
    private JavaMailSender mailSender;

    public NotificationService(EmailContentRepository emailContentRepository, JavaMailSender mailSender) {
        this.emailContentRepository = emailContentRepository;
        this.mailSender = mailSender;
    }

    void profileMessage(NotificationRequest request) {
        EmailContent emailContent = emailContentRepository.findByType(EmailType.PROFILE_MESSAGE);

        String userTo = request.getUserTo();
        String userFrom = request.getUserFrom();
        String messageText = request.getBody();

        String subject = String.format(emailContent.getSubject(), userFrom);
        String body = String.format(emailContent.getBody(), userTo, userFrom, messageText);

        sendEmail(request.getEmail(), subject, body);
   }

    void connectionRequest(NotificationRequest request) {
        sendConnectionNotification(request);
    }

    void connectionConfirmed(NotificationRequest request) {
        sendConnectionNotification(request);
    }

    private void sendConnectionNotification(NotificationRequest request) {
        EmailContent emailContent = emailContentRepository.findByType(EmailType.PROFILE_MESSAGE);

        String userTo = request.getUserTo();
        String userFrom = request.getUserFrom();

        String subject = String.format(emailContent.getSubject(), userFrom);
        String body = String.format(emailContent.getBody(), userTo, userFrom);

        sendEmail(request.getEmail(), subject, body);
    }


    private void sendEmail(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

}

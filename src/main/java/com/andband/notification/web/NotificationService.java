package com.andband.notification.web;

import com.andband.notification.persistence.EmailContent;
import com.andband.notification.persistence.EmailContentRepository;
import com.andband.notification.persistence.EmailType;
import com.andband.notification.properties.RuntimeProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private EmailContentRepository emailContentRepository;
    private JavaMailSender mailSender;
    private RuntimeProperties runtimeProperties;

    public NotificationService(EmailContentRepository emailContentRepository, JavaMailSender mailSender, RuntimeProperties runtimeProperties) {
        this.emailContentRepository = emailContentRepository;
        this.mailSender = mailSender;
        this.runtimeProperties = runtimeProperties;
    }

    void userRegistration(NotificationRequest request) {
        EmailContent emailContent = emailContentRepository.findByType(EmailType.REGISTRATION);

        String userName = request.getToProfileName();

        String tokenString = request.getText();
        String confirmRegistrationUrl = runtimeProperties.getAndbandUrl() + "/confirm-registration/" + tokenString;

        String body = String.format(emailContent.getBody(), userName, confirmRegistrationUrl);

        sendEmail(request.getEmail(), emailContent.getSubject(), body);
    }

    void confirmRegistration(NotificationRequest request) {
        EmailContent emailContent = emailContentRepository.findByType(EmailType.REGISTRATION_CONFIRMATION);

        String userName = request.getToProfileName();
        String subject = String.format(emailContent.getSubject(), userName);
        String body = String.format(emailContent.getBody(), userName);

        sendEmail(request.getEmail(), subject, body);
    }

    void profileMessage(NotificationRequest request) {
        EmailContent emailContent = emailContentRepository.findByType(EmailType.PROFILE_MESSAGE);

        String userTo = request.getToProfileName();
        String userFrom = request.getFromProfileName();
        String messageText = request.getText();

        String subject = String.format(emailContent.getSubject(), userFrom);
        String body = String.format(emailContent.getBody(), userTo, userFrom, messageText);

        sendEmail(request.getEmail(), subject, body);
    }

    void connectionRequest(NotificationRequest request) {
        sendConnectionNotification(request, EmailType.CONNECTION_REQUEST);
    }

    void connectionConfirmed(NotificationRequest request) {
        sendConnectionNotification(request, EmailType.CONNECTION_CONFIRMED);
    }

    void resetPassword(NotificationRequest request) {
        EmailContent emailContent = emailContentRepository.findByType(EmailType.RESET_PASSWORD);

        String userName = request.getToProfileName();

        String tokenString = request.getText();
        String resetPasswordUrl = runtimeProperties.getAndbandUrl() + "/reset-password/" + tokenString;

        String body = String.format(emailContent.getBody(), userName, resetPasswordUrl);

        sendEmail(request.getEmail(), emailContent.getSubject(), body);
    }

    private void sendConnectionNotification(NotificationRequest request, EmailType emailType) {
        EmailContent emailContent = emailContentRepository.findByType(emailType);

        String userTo = request.getToProfileName();
        String userFrom = request.getFromProfileName();

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

package com.andband.notification.web;

import com.andband.notification.persistence.EmailContent;
import com.andband.notification.persistence.EmailContentRepository;
import com.andband.notification.persistence.EmailType;
import com.andband.notification.properties.RuntimeProperties;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private EmailContentRepository mockEmailContentRepository;

    @Mock
    private JavaMailSender mockMailSender;

    @Mock
    private RuntimeProperties mockRuntimeProperties;

    @BeforeMethod
    public void init() {
        initMocks(this);
    }

    @Test
    public void testUserRegistration() {
        String email = "user1@email.com";
        String toProfileName = "User1";
        String fromProfileName = "User2";
        String tokenString = "TokenString123";
        String confirmRegistrationUrl = "http://andband.xyz/confirm-registration/";

        NotificationRequest notificationRequest = new NotificationRequestBuilder()
                .withEmail(email)
                .withToProfileName(toProfileName)
                .withFromProfileName(fromProfileName)
                .withText(tokenString)
                .build();


        EmailContent emailContent = new EmailContent();
        emailContent.setSubject("Confirm registration");
        emailContent.setBody("Hi %s, \n\nPlease use the link below to confirm your AndBand registration.\n\n%s\n\nThis link will remain active for two days.");

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(email);
        expectedMessage.setSubject("Confirm registration");
        expectedMessage.setText("Hi " + toProfileName + ", \n\nPlease use the link below to confirm your AndBand registration.\n\n" + confirmRegistrationUrl + tokenString + "\n\nThis link will remain active for two days.");

        when(mockEmailContentRepository.findByType(EmailType.REGISTRATION)).thenReturn(emailContent);
        when(mockRuntimeProperties.getConfirmRegistrationUrl()).thenReturn(confirmRegistrationUrl);

        notificationService.userRegistration(notificationRequest);

        verify(mockEmailContentRepository).findByType(EmailType.REGISTRATION);
        verify(mockRuntimeProperties).getConfirmRegistrationUrl();
        verify(mockMailSender).send(refEq(expectedMessage));
    }

    @Test
    public void testConfirmRegistration() {
        String email = "user1@email.com";
        String toProfileName = "User1";
        String fromProfileName = "User2";

        NotificationRequest notificationRequest = new NotificationRequestBuilder()
                .withEmail(email)
                .withToProfileName(toProfileName)
                .withFromProfileName(fromProfileName)
                .build();

        EmailContent emailContent = new EmailContent();
        emailContent.setSubject("Welcome to AndBand");
        emailContent.setBody("Hi %s, \n\nYou are now registered with AndBand.");

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(email);
        expectedMessage.setSubject("Welcome to AndBand");
        expectedMessage.setText("Hi " + toProfileName + ", \n\nYou are now registered with AndBand.");

        when(mockEmailContentRepository.findByType(EmailType.REGISTRATION_CONFIRMATION)).thenReturn(emailContent);

        notificationService.confirmRegistration(notificationRequest);

        verify(mockEmailContentRepository).findByType(EmailType.REGISTRATION_CONFIRMATION);
        verify(mockMailSender).send(refEq(expectedMessage));
    }

    @Test
    public void testProfileMessage() {
        String email = "user1@email.com";
        String toProfileName = "User1";
        String fromProfileName = "User2";
        String messageText = "Some message";

        NotificationRequest notificationRequest = new NotificationRequestBuilder()
                .withEmail(email)
                .withToProfileName(toProfileName)
                .withFromProfileName(fromProfileName)
                .withText(messageText)
                .build();

        EmailContent emailContent = new EmailContent();
        emailContent.setSubject("You received a new message from %s");
        emailContent.setBody("Hi %s, \n\nYou have received the following message from %s.\n\n%s");

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(email);
        expectedMessage.setSubject("You received a new message from " + fromProfileName);
        expectedMessage.setText("Hi " + toProfileName + ", \n\nYou have received the following message from " + fromProfileName + ".\n\n" + messageText);

        when(mockEmailContentRepository.findByType(EmailType.PROFILE_MESSAGE)).thenReturn(emailContent);

        notificationService.profileMessage(notificationRequest);

        verify(mockEmailContentRepository).findByType(EmailType.PROFILE_MESSAGE);
        verify(mockMailSender).send(refEq(expectedMessage));
    }

    @Test
    public void testConnectionRequest() {
        String email = "user1@email.com";
        String toProfileName = "User1";
        String fromProfileName = "User2";

        NotificationRequest notificationRequest = new NotificationRequestBuilder()
                .withEmail(email)
                .withToProfileName(toProfileName)
                .withFromProfileName(fromProfileName)
                .build();

        EmailContent emailContent = new EmailContent();
        emailContent.setSubject("You received connection request from %s");
        emailContent.setBody("Hi %s, \n\nYou have received connection request from %s.\n\nGo to andband.xyz to confirm the request.");

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(email);
        expectedMessage.setSubject("You received connection request from " + fromProfileName);
        expectedMessage.setText("Hi " + toProfileName + ", \n\nYou have received connection request from " + fromProfileName + ".\n\n" + "Go to andband.xyz to confirm the request.");

        when(mockEmailContentRepository.findByType(EmailType.CONNECTION_REQUEST)).thenReturn(emailContent);

        notificationService.connectionRequest(notificationRequest);

        verify(mockEmailContentRepository).findByType(EmailType.CONNECTION_REQUEST);
        verify(mockMailSender).send(refEq(expectedMessage));
    }

    @Test
    public void testConnectionConfirmed() {
        String email = "user1@email.com";
        String toProfileName = "User1";
        String fromProfileName = "User2";

        NotificationRequest notificationRequest = new NotificationRequestBuilder()
                .withEmail(email)
                .withToProfileName(toProfileName)
                .withFromProfileName(fromProfileName)
                .build();

        EmailContent emailContent = new EmailContent();
        emailContent.setSubject("You are now connected with %s");
        emailContent.setBody("Hi %s, \n\nYou are now connected with %s");

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(email);
        expectedMessage.setSubject("You are now connected with " + fromProfileName);
        expectedMessage.setText("Hi " + toProfileName + ", \n\nYou are now connected with " + fromProfileName);

        when(mockEmailContentRepository.findByType(EmailType.CONNECTION_CONFIRMED)).thenReturn(emailContent);

        notificationService.connectionConfirmed(notificationRequest);

        verify(mockEmailContentRepository).findByType(EmailType.CONNECTION_CONFIRMED);
        verify(mockMailSender).send(refEq(expectedMessage));
    }

}

package com.andband.notification.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/user-registration")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void userRegistration(@RequestBody NotificationRequest request) {
        notificationService.userRegistration(request);
    }

    @PostMapping("/confirm-registration")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmRegistration(@RequestBody NotificationRequest request) {
        notificationService.confirmRegistration(request);
    }

    @PostMapping("/profile-message")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void profileMessage(@RequestBody NotificationRequest request) {
        notificationService.profileMessage(request);
    }

    @PostMapping("/connection-request")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void connectionRequest(@RequestBody NotificationRequest request) {
        notificationService.connectionRequest(request);
    }

    @PostMapping("/connection-confirmed")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void connectionConfirmed(@RequestBody NotificationRequest request) {
        notificationService.connectionConfirmed(request);
    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resetPassword(@RequestBody NotificationRequest request) {
        notificationService.resetPassword(request);
    }

}

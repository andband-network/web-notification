package com.andband.notification.web;

public class NotificationRequestBuilder {

    private NotificationRequest notificationRequest;

    public NotificationRequestBuilder() {
        notificationRequest = new NotificationRequest();
    }

    public NotificationRequestBuilder withEmail(String email) {
        notificationRequest.setEmail(email);
        return this;
    }

    public NotificationRequestBuilder withToProfileName(String toProfileName) {
        notificationRequest.setToProfileName(toProfileName);
        return this;
    }

    public NotificationRequestBuilder withFromProfileName(String fromProfileName) {
        notificationRequest.setFromProfileName(fromProfileName);
        return this;
    }

    public NotificationRequestBuilder withText(String text) {
        notificationRequest.setText(text);
        return this;
    }

    public NotificationRequest build() {
        return notificationRequest;
    }

}

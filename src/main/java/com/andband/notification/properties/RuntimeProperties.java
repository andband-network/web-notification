package com.andband.notification.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:com/andband/notification/properties/runtime-${runtime_environment:prod}.properties")
public class RuntimeProperties {

    @Value("${confirm-registration-url}")
    private String confirmRegistrationUrl;

    public String getConfirmRegistrationUrl() {
        return confirmRegistrationUrl;
    }

    public void setConfirmRegistrationUrl(String confirmRegistrationUrl) {
        this.confirmRegistrationUrl = confirmRegistrationUrl;
    }
}

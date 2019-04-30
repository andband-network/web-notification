package com.andband.notification.persistence;

import org.springframework.data.repository.CrudRepository;

public interface EmailContentRepository extends CrudRepository<EmailContent, Long> {

    EmailContent findByType(EmailType type);

}

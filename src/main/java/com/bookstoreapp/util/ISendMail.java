package com.bookstoreapp.util;

import com.bookstoreapp.dto.NotificationDto;

import javax.mail.MessagingException;

public interface ISendMail {

    String sendMail(NotificationDto notificationDto) throws MessagingException;
}

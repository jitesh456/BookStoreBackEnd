package com.bookstoreapp.dto;

public class NotificationDto {
    public String recipientAddress;
    public String subject;
    public String body;

    public NotificationDto(String recipientAddress, String subject, String body) {
        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.body = body;
    }
}

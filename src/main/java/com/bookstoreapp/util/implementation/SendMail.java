package com.bookstoreapp.util.implementation;

import com.bookstoreapp.dto.NotificationDto;
import com.bookstoreapp.util.ISendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class SendMail implements ISendMail {

    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public String sendMail(NotificationDto notificationDto) throws MessagingException {
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(message);
        messageHelper.setTo(notificationDto.recipientAddress);
        messageHelper.setSubject(notificationDto.subject);
        messageHelper.setText(notificationDto.body);
        javaMailSender.send(message);
        return "Mail Sent Successfully";
    }
}

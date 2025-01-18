package ru.slavmirol.esa_lr3rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.slavmirol.esa_lr3rest.model.Log;
import ru.slavmirol.esa_lr3rest.model.Notification;
import ru.slavmirol.esa_lr3rest.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificationRepository notificationRepository;

    public void checkAndSendNotifications(CourseService.EntityChangeMessage message) {
        Notification notification = new Notification("slavmirol@gmail.com", message.toString());
        notificationRepository.save(notification);
        sendEmail(notification.getEmail(), message);
    }

    private void sendEmail(String email, CourseService.EntityChangeMessage message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Change Notification");
        mailMessage.setText("Change detected: " + message);
        mailSender.send(mailMessage);
    }
}

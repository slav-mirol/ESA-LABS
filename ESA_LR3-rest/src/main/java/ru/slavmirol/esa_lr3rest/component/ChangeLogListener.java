package ru.slavmirol.esa_lr3rest.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.slavmirol.esa_lr3rest.model.Log;
import ru.slavmirol.esa_lr3rest.service.CourseService;
import ru.slavmirol.esa_lr3rest.service.LogService;
import ru.slavmirol.esa_lr3rest.service.NotificationService;

import java.io.IOException;

@Component
public class ChangeLogListener {

    @Autowired
    private LogService logService;

    @Autowired
    private NotificationService notificationService;

    @JmsListener(destination = "entityChangeQueue")
    public void onMessage(byte[] log) throws IOException {
        // Преобразуем байты обратно в объект Log
        ObjectMapper objectMapper = new ObjectMapper();
        CourseService.EntityChangeMessage message = objectMapper.readValue(log, CourseService.EntityChangeMessage.class);
        // Теперь можно работать с объектом log
        System.out.println("LOG from onMessage\n" + message.toString());

        logService.logChange(
                message.getEntityName(),
                message.getEntityId(),
                message.getChangeType(),
                message.getChangeDetails()
        );
        // Отправка уведомлений
        notificationService.checkAndSendNotifications(message);
    }
}
package ru.slavmirol.esa_lr3rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.slavmirol.esa_lr3rest.model.Log;
import ru.slavmirol.esa_lr3rest.repository.LogRepository;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public void logChange(String entityName, Long entityId, String changeType, String changeDetails) {
        Log log = new Log();
        log.setEntityName(entityName);
        log.setEntityId(entityId);
        log.setChangeType(changeType);
        log.setChangeDetails(changeDetails);
        System.out.println("LOG from onMessage" + log);
        logRepository.save(log);
    }
}
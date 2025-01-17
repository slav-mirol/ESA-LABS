package ru.slavmirol.esa_lr2spring.service;

import ru.slavmirol.esa_lr2spring.model.Enrollment;
import ru.slavmirol.esa_lr2spring.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }
}
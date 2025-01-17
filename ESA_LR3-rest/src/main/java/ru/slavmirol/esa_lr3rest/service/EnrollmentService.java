package ru.slavmirol.esa_lr3rest.service;

import ru.slavmirol.esa_lr3rest.model.Course;
import ru.slavmirol.esa_lr3rest.model.Enrollment;
import ru.slavmirol.esa_lr3rest.model.Student;
import ru.slavmirol.esa_lr3rest.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public void enrollStudentToCourse(Student student, Course course) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }
}
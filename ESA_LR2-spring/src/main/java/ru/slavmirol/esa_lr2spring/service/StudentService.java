package ru.slavmirol.esa_lr2spring.service;

import ru.slavmirol.esa_lr2spring.model.Course;
import ru.slavmirol.esa_lr2spring.model.Student;
import ru.slavmirol.esa_lr2spring.repository.EnrollmentRepository;
import ru.slavmirol.esa_lr2spring.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(StudentRepository studentRepository,
                          EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Course> findCoursesByStudentId(Long studentId) {
        return enrollmentRepository.findCoursesByStudentId(studentId);
    }
}
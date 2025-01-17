package ru.slavmirol.esa_lr2spring.service;

import ru.slavmirol.esa_lr2spring.model.Course;
import ru.slavmirol.esa_lr2spring.model.Enrollment;
import ru.slavmirol.esa_lr2spring.model.Student;
import ru.slavmirol.esa_lr2spring.repository.CourseRepository;
import ru.slavmirol.esa_lr2spring.repository.EnrollmentRepository;
import ru.slavmirol.esa_lr2spring.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository,
                         EnrollmentRepository enrollmentRepository,
                         StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public List<Student> findStudentsByCourseId(Long courseId) {
        return enrollmentRepository.findStudentsByCourseId(courseId);
    }

    public void enrollStudent(Long courseId, Long studentId) {
        // Найти студента и курс
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + courseId));

        // Создать запись о зачислении
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        // Сохранить запись
        enrollmentRepository.save(enrollment);
    }
}
package ru.slavmirol.esa_lr3rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import ru.slavmirol.esa_lr3rest.model.Enrollment;
import ru.slavmirol.esa_lr3rest.model.Student;
import ru.slavmirol.esa_lr3rest.repository.StudentRepository;
import ru.slavmirol.esa_lr3rest.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private JmsTemplate jmsTemplate;

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(StudentRepository studentRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        Student createdStudent = studentRepository.save(student);
        jmsTemplate.convertAndSend("entityChangeQueue", new CourseService.EntityChangeMessage(
                "Course",
                createdStudent.getId(),
                "INSERT",
                createdStudent.toString()
        ));
        return createdStudent;
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student curStudent = studentRepository.findById(id)
                             .map(student -> {
                                    student.setFirstName(updatedStudent.getFirstName());
                                    student.setLastName(updatedStudent.getLastName());
                                    return studentRepository.save(student);
                                 }).orElseThrow(() -> new RuntimeException("Student not found"));

        jmsTemplate.convertAndSend("entityChangeQueue", new CourseService.EntityChangeMessage(
                "Student",
                curStudent.getId(),
                "UPDATE",
                curStudent.toString()
        ));

        return curStudent;
    }

    public void deleteStudent(Long id) {
        // Находим все записи в таблице Enrollments по courseId
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(id);

        // Если записи найдены, удаляем их
        if (!enrollments.isEmpty()) {
            enrollmentRepository.deleteAll(enrollments);
        }

        Student deletedStudent = studentRepository.findById(id).orElse(null);
        jmsTemplate.convertAndSend("entityChangeQueue", new CourseService.EntityChangeMessage(
                "Student",
                deletedStudent.getId(),
                "DELETE",
                deletedStudent.toString()
        ));

        // Удаляем студента
        studentRepository.deleteById(id);
    }
}
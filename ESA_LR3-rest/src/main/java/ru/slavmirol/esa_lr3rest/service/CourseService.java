package ru.slavmirol.esa_lr3rest.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import ru.slavmirol.esa_lr3rest.model.Course;
import ru.slavmirol.esa_lr3rest.model.Enrollment;
import ru.slavmirol.esa_lr3rest.model.Student;
import ru.slavmirol.esa_lr3rest.repository.StudentRepository;
import ru.slavmirol.esa_lr3rest.repository.CourseRepository;
import ru.slavmirol.esa_lr3rest.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private JmsTemplate jmsTemplate;

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


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) {
        Course createdCourse = courseRepository.save(course);
        jmsTemplate.convertAndSend("entityChangeQueue", new EntityChangeMessage(
                "Course",
                createdCourse.getId(),
                "INSERT",
                createdCourse.toString()
        ));
        return createdCourse;
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        Course curCourse = courseRepository.findById(id)
                .map(course -> {
                    course.setName(updatedCourse.getName());
                    course.setDescription(updatedCourse.getDescription());
                    return courseRepository.save(course);
                }).orElseThrow(() -> new RuntimeException("Course not found"));
        jmsTemplate.convertAndSend("entityChangeQueue", new EntityChangeMessage(
                "Course",
                curCourse.getId(),
                "UPDATE",
                curCourse.toString()
        ));
        return curCourse;
    }

    public void deleteCourse(Long id) {
        // Находим все записи в таблице Enrollments по courseId
        List<Enrollment> enrollments = enrollmentRepository.findByCourseId(id);

        // Если записи найдены, удаляем их
        if (!enrollments.isEmpty()) {
            enrollmentRepository.deleteAll(enrollments);
        }

        Course deletedCourse = courseRepository.findById(id).orElse(null);
        jmsTemplate.convertAndSend("entityChangeQueue", new EntityChangeMessage(
                "Course",
                deletedCourse.getId(),
                "DELETE",
                deletedCourse.toString()
        ));

        // Удаляем курс
        courseRepository.deleteById(id);
    }

    public Enrollment enrollStudent(Long courseId, Long studentId) {
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
        Enrollment createdEnrollment = enrollmentRepository.save(enrollment);

        jmsTemplate.convertAndSend("entityChangeQueue", new EntityChangeMessage(
                "Enrollment",
                createdEnrollment.getId(),
                "INSERT",
                createdEnrollment.toString()
        ));

        return enrollment;

    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"entityName","entityId","changeType","changeDetails"})
    public static class EntityChangeMessage {
        private String entityName;
        private Long entityId;
        private String changeType;
        private String changeDetails;

        public EntityChangeMessage(String entityName, Long entityId, String changeType, String changeDetails) {
            this.entityName = entityName;
            this.entityId = entityId;
            this.changeType = changeType;
            this.changeDetails = changeDetails;
        }

        public EntityChangeMessage() {

        }

        // Геттеры и сеттеры
        public String getEntityName() {
            return entityName;
        }

        public void setEntityName(String entityName) {
            this.entityName = entityName;
        }

        public Long getEntityId() {
            return entityId;
        }

        public void setEntityId(Long entityId) {
            this.entityId = entityId;
        }

        public String getChangeType() {
            return changeType;
        }

        public void setChangeType(String changeType) {
            this.changeType = changeType;
        }

        public String getChangeDetails() {
            return changeDetails;
        }

        public void setChangeDetails(String changeDetails) {
            this.changeDetails = changeDetails;
        }

        @Override
        public String toString() {
            return "EntityChangeMessage{" +
                    "entityName='" + entityName + '\'' +
                    ", entityId=" + entityId +
                    ", changeType='" + changeType + '\'' +
                    ", changeDetails='" + changeDetails + '\'' +
                    '}';
        }
    }
}


package ru.slavmirol.esa_lr2spring.repository;

import ru.slavmirol.esa_lr2spring.model.Enrollment;
import ru.slavmirol.esa_lr2spring.model.Student;
import ru.slavmirol.esa_lr2spring.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("SELECT e.student FROM Enrollment e WHERE e.course.id = :courseId")
    List<Student> findStudentsByCourseId(Long courseId);

    @Query("SELECT e.course FROM Enrollment e WHERE e.student.id = :studentId")
    List<Course> findCoursesByStudentId(Long studentId);
}
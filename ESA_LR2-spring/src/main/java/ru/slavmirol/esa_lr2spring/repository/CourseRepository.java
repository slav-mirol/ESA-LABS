package ru.slavmirol.esa_lr2spring.repository;

import ru.slavmirol.esa_lr2spring.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}
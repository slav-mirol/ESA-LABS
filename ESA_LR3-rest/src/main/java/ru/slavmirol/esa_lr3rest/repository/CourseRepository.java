package ru.slavmirol.esa_lr3rest.repository;

import ru.slavmirol.esa_lr3rest.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {}
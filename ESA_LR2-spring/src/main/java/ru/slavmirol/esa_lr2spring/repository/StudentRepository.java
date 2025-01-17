package ru.slavmirol.esa_lr2spring.repository;

import ru.slavmirol.esa_lr2spring.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}


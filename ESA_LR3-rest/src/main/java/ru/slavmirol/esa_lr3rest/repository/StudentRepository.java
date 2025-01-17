package ru.slavmirol.esa_lr3rest.repository;

import ru.slavmirol.esa_lr3rest.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findProductById(Long id);
}
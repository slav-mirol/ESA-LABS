package ru.slavmirol.esa_lr3rest.repository;

import ru.slavmirol.esa_lr3rest.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {}
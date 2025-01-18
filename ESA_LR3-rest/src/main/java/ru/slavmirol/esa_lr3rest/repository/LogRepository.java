package ru.slavmirol.esa_lr3rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.slavmirol.esa_lr3rest.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {}

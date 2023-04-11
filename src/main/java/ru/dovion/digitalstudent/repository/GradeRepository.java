package ru.dovion.digitalstudent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dovion.digitalstudent.model.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}

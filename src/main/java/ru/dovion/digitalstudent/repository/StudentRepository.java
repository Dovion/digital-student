package ru.dovion.digitalstudent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dovion.digitalstudent.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}

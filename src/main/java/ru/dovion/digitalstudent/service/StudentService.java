package ru.dovion.digitalstudent.service;

import net.sf.jasperreports.engine.JRException;
import ru.dovion.digitalstudent.model.dto.StudentDto;
import ru.dovion.digitalstudent.model.dto.StudentOutDto;

import java.io.FileNotFoundException;
import java.util.List;

public interface StudentService {

    StudentOutDto createStudent(StudentDto StudentDto);

    StudentOutDto updateStudent(StudentDto StudentDto, Long id);

    StudentOutDto getStudent(Long id);

    void deleteStudent(Long id);

    List<StudentOutDto> getAll();

    void makeReportForAllStudents() throws JRException, FileNotFoundException;

    void makeReportForStudent(Long studentId) throws JRException, FileNotFoundException;
}

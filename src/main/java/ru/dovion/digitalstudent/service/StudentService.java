package ru.dovion.digitalstudent.service;

import ru.dovion.digitalstudent.model.dto.StudentDto;
import ru.dovion.digitalstudent.model.dto.StudentOutDto;

import java.util.List;

public interface StudentService {

    public StudentOutDto createStudent(StudentDto StudentDto);

    public StudentOutDto updateStudent(StudentDto StudentDto, Long id);

    public StudentOutDto getStudent(Long id);

    public void deleteStudent(Long id);

    public List<StudentOutDto> getAll();
}

package ru.dovion.digitalstudent.model.mapper;

import org.modelmapper.ModelMapper;
import ru.dovion.digitalstudent.model.Student;
import ru.dovion.digitalstudent.model.dto.StudentDto;
import ru.dovion.digitalstudent.model.dto.StudentOutDto;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    private final static ModelMapper modelMapper = new ModelMapper();

    public static Student fromDto(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }

    public static StudentOutDto studentToOutDto(Student student) {
        if (student.getGrades() == null) {
            student.setGrades(List.of());
        }
        return new StudentOutDto(student.getId(), student.getFirstName(), student.getSecondName(), student.getAge(),
                student.getEmail(), student.getGrades()
                                           .stream()
                                           .map(grade -> GradeMapper.gradeToOutDto(grade))
                                           .collect(Collectors.toList()));
    }
}

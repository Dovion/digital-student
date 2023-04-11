package ru.dovion.digitalstudent.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dovion.digitalstudent.model.Student;
import ru.dovion.digitalstudent.model.dto.StudentDto;
import ru.dovion.digitalstudent.model.dto.StudentOutDto;
import ru.dovion.digitalstudent.model.mapper.StudentMapper;
import ru.dovion.digitalstudent.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentOutDto createStudent(StudentDto studentDto) {
        log.info("Создание нового студента в БД");
        Student student = StudentMapper.fromDto(studentDto);
        return StudentMapper.studentToOutDto(studentRepository.saveAndFlush(student));
    }

    @Override
    public StudentOutDto updateStudent(StudentDto studentDto, Long id) {
        log.info("Обновление данных студента");
        var student = studentRepository.findById(id)
                                       .orElseThrow(() -> new EntityNotFoundException(
                                               "Передан неверный идентификатор студента"));
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());
        student.setFirstName(studentDto.getFirstName());
        student.setSecondName(studentDto.getSecondName());
        return StudentMapper.studentToOutDto(studentRepository.saveAndFlush(student));
    }

    @Override
    public StudentOutDto getStudent(Long id) {
        log.info("Получение данных студента с идентификатором: " + id);
        var foundedStudent = studentRepository.findById(id)
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Передан неверный идентификатор студента"));
        return StudentMapper.studentToOutDto(foundedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Удаление данных студента с идентификатором: " + id);
        var foundedStudent = studentRepository.findById(id)
                                              .orElseThrow(() -> new EntityNotFoundException(
                                                      "Передан неверный идентификатор студента"));
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentOutDto> getAll() {
        log.info("Получение данных всех студентов");
        return studentRepository.findAll()
                                .stream()
                                .map(student -> StudentMapper.studentToOutDto(student))
                                .collect(Collectors.toList());
    }
}

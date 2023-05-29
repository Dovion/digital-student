package ru.dovion.digitalstudent.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dovion.digitalstudent.exception.InvalidDataException;
import ru.dovion.digitalstudent.model.dto.StudentDto;
import ru.dovion.digitalstudent.model.dto.StudentOutDto;
import ru.dovion.digitalstudent.service.StudentService;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @Operation(summary = "Создать нового студента в базе данных")
    public StudentOutDto createStudent(@RequestBody @Validated StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return studentService.createStudent(studentDto);
    }

    @PatchMapping
    @Operation(summary = "Обновить данные студента")
    public StudentOutDto updateStudent(@RequestBody @Validated StudentDto studentDto, @RequestParam Long id,
                                       BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }

        return studentService.updateStudent(studentDto, id);
    }

    @GetMapping
    @Operation(summary = "Получить данные студента по идентификатору")
    public StudentOutDto getStudent(@RequestParam Long id) {
        return studentService.getStudent(id);
    }

    @DeleteMapping
    @Operation(summary = "Удалить студента из базы данных")
    public void deleteStudent(@RequestParam Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Получить информацию обо всех студентах")
    public List<StudentOutDto> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/report/all")
    @Operation(summary = "Сгенерировать список всех студентов в формате pdf")
    public void makeReport() throws JRException, FileNotFoundException {
        studentService.makeReportForAllStudents();
    }

    @GetMapping("/report/student")
    @Operation(summary = "Сгенерировать отчёт об успеваемости студента по идентификатору в формате pdf")
    public void makeReportForStudent(@RequestParam Long studentId) throws JRException, FileNotFoundException {
        studentService.makeReportForStudent(studentId);
    }
}

package ru.dovion.digitalstudent.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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
import ru.dovion.digitalstudent.model.dto.GradeDto;
import ru.dovion.digitalstudent.model.dto.GradeOutDto;
import ru.dovion.digitalstudent.service.GradeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/grade")
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    @Operation(summary = "Создать новую оценку (Потребуется идентификатор студента)")
    public GradeOutDto createGrade(@RequestBody @Validated GradeDto gradeDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return gradeService.createGrade(gradeDto);
    }

    @PatchMapping
    @Operation(summary = "Обновить данные об оценке (Потребуется идентификатор студента)")
    public GradeOutDto updateGrade(@RequestBody @Validated GradeDto gradeDto, @RequestParam Long id,
                                   BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return gradeService.updateGrade(gradeDto, id);
    }

    @GetMapping
    @Operation(summary = "Получить данные об оценке по идентификатору")
    public GradeOutDto getGrade(@RequestParam Long id) {
        return gradeService.getGrade(id);
    }

    @DeleteMapping
    @Operation(summary = "Удалить оценку")
    public void deleteGrade(@RequestParam Long id) {
        gradeService.deleteGrade(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Получить данные обо всех оценках")
    public List<GradeOutDto> getAll() {
        return gradeService.getAll();
    }
}

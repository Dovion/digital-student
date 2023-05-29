package ru.dovion.digitalstudent.service;

import ru.dovion.digitalstudent.model.dto.GradeDto;
import ru.dovion.digitalstudent.model.dto.GradeOutDto;

import java.util.List;

public interface GradeService {

    GradeOutDto createGrade(GradeDto gradeDto);

    GradeOutDto updateGrade(GradeDto gradeDto, Long id);

    GradeOutDto getGrade(Long id);

    void deleteGrade(Long id);

    List<GradeOutDto> getAll();
}

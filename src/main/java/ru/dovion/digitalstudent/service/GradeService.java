package ru.dovion.digitalstudent.service;

import ru.dovion.digitalstudent.model.dto.GradeDto;
import ru.dovion.digitalstudent.model.dto.GradeOutDto;

import java.util.List;

public interface GradeService {

    public GradeOutDto createGrade(GradeDto gradeDto);

    public GradeOutDto updateGrade(GradeDto gradeDto, Long id);

    public GradeOutDto getGrade(Long id);

    public void deleteGrade(Long id);

    public List<GradeOutDto> getAll();
}

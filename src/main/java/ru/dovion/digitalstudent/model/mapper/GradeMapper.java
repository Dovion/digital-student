package ru.dovion.digitalstudent.model.mapper;

import org.modelmapper.ModelMapper;
import ru.dovion.digitalstudent.model.Grade;
import ru.dovion.digitalstudent.model.dto.GradeDto;
import ru.dovion.digitalstudent.model.dto.GradeOutDto;

public class GradeMapper {

    private final static ModelMapper modelMapper = new ModelMapper();

    public static Grade fromDto(GradeDto gradeDto) {
        return modelMapper.map(gradeDto, Grade.class);
    }

    public static GradeOutDto gradeToOutDto(Grade grade) {
        return new GradeOutDto(grade.getId(), grade.getScore(), grade.getDate(), grade.getSubject(),
                grade.getStudent().getFirstName() + " " + grade.getStudent().getSecondName());
    }
}

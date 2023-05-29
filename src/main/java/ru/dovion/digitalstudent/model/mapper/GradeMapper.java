package ru.dovion.digitalstudent.model.mapper;

import ru.dovion.digitalstudent.model.Grade;
import ru.dovion.digitalstudent.model.dto.GradeDto;
import ru.dovion.digitalstudent.model.dto.GradeOutDto;
import ru.dovion.digitalstudent.model.dto.GradeReportDto;

public class GradeMapper {

    public static Grade fromDto(GradeDto gradeDto) {
        return new Grade(null, gradeDto.getScore(), null, null, gradeDto.getSubject());
    }

    public static GradeOutDto gradeToOutDto(Grade grade) {
        return new GradeOutDto(grade.getId(), grade.getScore(), grade.getDate(), grade.getSubject(),
                grade.getStudent().getFirstName() + " " + grade.getStudent().getSecondName());
    }

    public static GradeReportDto toGradeReportDto(Grade grade) {
        return new GradeReportDto(grade.getId(), grade.getScore(), grade.getDate(), grade.getSubject().toString());
    }
}

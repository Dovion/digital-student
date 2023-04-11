package ru.dovion.digitalstudent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dovion.digitalstudent.model.Subject;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GradeOutDto {

    private Long id;

    private Integer score;

    private LocalDate date;

    private Subject subject;

    private String studentName;
}
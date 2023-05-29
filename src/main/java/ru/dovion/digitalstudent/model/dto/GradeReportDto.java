package ru.dovion.digitalstudent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeReportDto {

    private Long id;

    private Integer score;

    private LocalDate date;

    private String subject;


}

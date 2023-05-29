package ru.dovion.digitalstudent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentReportDto {

    private Long id;

    private String firstName;

    private String secondName;

    private Integer age;

    private String email;
}

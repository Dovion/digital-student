package ru.dovion.digitalstudent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutDto implements Serializable {

    private Long id;

    private String firstName;

    private String secondName;

    private Integer age;

    private String email;

    private List<GradeOutDto> grades;
}
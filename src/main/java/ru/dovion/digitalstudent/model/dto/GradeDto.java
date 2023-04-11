package ru.dovion.digitalstudent.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.dovion.digitalstudent.model.Subject;

import java.io.Serializable;

@Data
public class GradeDto implements Serializable {
    @NotNull(message = "Оценка не может быть пустой")
    @Min(value = 2, message = "Минимальное значение оценки равно двум")
    @Max(value = 5, message = "Максимальное значение оценки равно пяти")
    private final Integer score;

    @NotNull(message = "Идентификатор студента не может быть пустым")
    @Schema(defaultValue = "1")
    private final Long studentId;

    @NotNull(message = "Название предмета не может быть пустым")
    private final Subject subject;
}
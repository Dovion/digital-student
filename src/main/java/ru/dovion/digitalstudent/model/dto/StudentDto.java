package ru.dovion.digitalstudent.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDto implements Serializable {

    @NotBlank(message = "Имя студента не может быть пустым")
    @Schema(defaultValue = "Ivan")
    private final String firstName;

    @NotBlank(message = "Фамилия студента не может быть пустой")
    @Schema(defaultValue = "Petrov")
    private final String secondName;

    @Min(value = 14, message = "Минимальный возраст студента равен 14")
    @Max(value = 100, message = "Максимальный возраст студента не должен превышать 100")
    @NotNull(message = "Возраст студента не должен быть пустым")
    @Schema(defaultValue = "18")
    private final Integer age;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$",
           message = "Email студента должен соответствовать формату: 'example@mail.ru'")
    @NotBlank(message = "Email студента не может быть пустым")
    @Schema(defaultValue = "usermail@mail.ru")
    private final String email;
}
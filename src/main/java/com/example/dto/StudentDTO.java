package com.example.dto;

import com.example.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    private Integer id;

    private String name;

    private String surname;

    private Integer level;

    private Integer age;

    private Gender gender;

    private LocalDate createdDate;
}

package com.example.entity;

import com.example.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "student")
@Getter
@Setter
public class StudentEntity {

    @Id
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private Integer level;

    @Column
    private Integer age;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "created_date")
    private LocalDate createdDate;
}

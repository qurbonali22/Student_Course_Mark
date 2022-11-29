package com.example.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "course")
@Getter
@Setter
public class CourseEntity {

    @Id
    @Column
    private Integer id;

    @Column
    private String name;

    @Column
    private Long price;

    @Column
    private Integer duration;

    @Column(name = "created_date")
    private LocalDate createdDate;
}

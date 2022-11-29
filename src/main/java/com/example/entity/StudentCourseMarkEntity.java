package com.example.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "student_course_mark")
@Getter
@Setter
public class StudentCourseMarkEntity {

    @Id
    @Column
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity student;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column
    private Float mark;

    @Column(name = "created_date")
    private LocalDate createdDate;

}

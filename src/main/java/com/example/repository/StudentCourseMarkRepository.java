package com.example.repository;


import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseMarkEntity;
import com.example.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentCourseMarkRepository extends PagingAndSortingRepository<StudentCourseMarkEntity, Integer> {

    @Query("FROM StudentCourseMarkEntity where student =?1 and createdDate =?2")
    public List<StudentCourseMarkEntity> findByStudentAndCreatedDate(StudentEntity student, LocalDate date);

    @Query("FROM StudentCourseMarkEntity where student =?1 and createdDate between ?1 and ?2")
    public List<StudentCourseMarkEntity> findByStudentAndCreatedDateBetween(StudentEntity student, LocalDate dateFrom, LocalDate dateTo);

    @Query("FROM StudentCourseMarkEntity order by createdDate desc ")
    public List<StudentCourseMarkEntity> findByStudentOrderByCreatedDateDesc(StudentEntity student);

    @Query("FROM StudentCourseMarkEntity where student =?1 and course =?2 order by createdDate desc ")
    public List<StudentCourseMarkEntity> findByStudentAndCourseOrderByCreatedDateDesc(StudentEntity student, CourseEntity course);

//    .....
    @Query("FROM StudentCourseMarkEntity where student =?1 order by createdDate desc ")
    public List<StudentCourseMarkEntity> findFirstByStudentOrderByCreatedDateDesc(StudentEntity student);

    @Query("FROM StudentCourseMarkEntity where student =?1 order by mark desc ")
    public List<StudentCourseMarkEntity> findTop3ByStudentOrderByMarkDesc(StudentEntity student);

//    .....
    @Query("FROM StudentCourseMarkEntity where student =?1 order by createdDate asc ")
    public List<StudentCourseMarkEntity> findFirstByStudentOrderByCreatedDateAsc(StudentEntity student);

//    ....
    @Query("FROM StudentCourseMarkEntity where student =?1 and course =?2 order by createdDate desc")
    public List<StudentCourseMarkEntity> findFirstByStudentAndCourseOrderByCreatedDateAsc(StudentEntity student, CourseEntity course);

//    ....
    @Query("FROM StudentCourseMarkEntity where student =?1 and course =?2 order by mark desc ")
    public List<StudentCourseMarkEntity> findFirstByStudentAndCourseOrderByMarkDesc(StudentEntity student, CourseEntity course);


    @Query("select avg(mark) from StudentCourseMarkEntity where student =?1")
    public Float findByStudentAvgMark(StudentEntity student);

    @Query("select count (mark) from StudentCourseMarkEntity where student =?1 and mark >?2")
    public Integer test18 (StudentEntity student, Float mark);

    @Query("SELECT avg (mark) FROM StudentCourseMarkEntity WHERE course =?1")
    public Float test20(CourseEntity course);

    @Query("SELECT count (mark) FROM StudentCourseMarkEntity WHERE course =?1")
    public Integer test21(CourseEntity course);

    @Query("select avg(mark) from StudentCourseMarkEntity where student =?1 and course =?2")
    public Float findByStudentAvgMark(StudentEntity student, CourseEntity course);

    @Query("select max (mark) from StudentCourseMarkEntity where course =?1")
    public Float findByCourseMaxMark(CourseEntity course);




    @Query("FROM StudentCourseMarkEntity where id =?1")
    Optional<StudentCourseMarkEntity> findById(Integer id);

    void save(StudentCourseMarkEntity entity);

    @Transactional
    @Query("delete from StudentCourseMarkEntity where id =?1")
    void delete(Integer id);

    @Query("FROM StudentCourseMarkEntity")
    Iterable<StudentCourseMarkEntity> findAll();


    Page<StudentCourseMarkEntity> findByStudent(StudentEntity student, Pageable pageable);

    Page<StudentCourseMarkEntity> findByCourse(CourseEntity course, Pageable pageable);


}

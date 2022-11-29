package com.example.repository;

import com.example.entity.StudentEntity;
import com.example.enums.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Integer> {

    @Query("FROM StudentEntity where name =?1")
    public List<StudentEntity> findByName(String name);

    @Query("FROM StudentEntity where surname =?1")
    public List<StudentEntity> findBySurname(String surname);

    @Query("FROM StudentEntity where age =?1")
    public List<StudentEntity> findByAge(Integer age);

    @Query("FROM StudentEntity where level =?1")
    public List<StudentEntity> findByLevel(Integer level);


    @Query("FROM StudentEntity where gender =?1")
    public List<StudentEntity> findByGender(Gender gender);

    @Query("FROM StudentEntity where createdDate = ?1")
    public List<StudentEntity> findByCreatedDate(LocalDate date);

    @Query("FROM StudentEntity where createdDate between ?1 and ?2")
    public List<StudentEntity> findByCreatedDateBetween(LocalDate dateFrom, LocalDate dateTo);

    @Query("FROM StudentEntity where id =?1")
    Optional<StudentEntity> findById(Integer id);

    @Transactional
    @Query("DELETE from StudentEntity where id =?1")
    void deleteById(Integer id);

    void save(StudentEntity entity);

    @Query("FROM StudentEntity ")
    Iterable<StudentEntity> getAll();

    Page<StudentEntity> findByLevel(Integer level, Pageable pageable);

    Page<StudentEntity> findByGender(Gender gender, Pageable pageable);
}

package com.example.repository;

import com.example.entity.CourseEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<CourseEntity, Integer> {

    @Query("FROM CourseEntity where name =?1")
    public List<CourseEntity> findByName(String name);

    @Query("FROM CourseEntity where price =?1")
    public List<CourseEntity> findByPrice(Long price);

    @Query("FROM CourseEntity where duration =?1")
    public List<CourseEntity> findByDuration(Integer duration);

    @Query("FROM CourseEntity where price between ?1 and ?2")
    public List<CourseEntity> findByPriceBetween(Long priceFrom, Long priceTo);

    @Query("FROM CourseEntity where createdDate between ?1 and ?2")
    public List<CourseEntity> findByCreatedDateBetween(LocalDate dateFrom, LocalDate dateTo);


    @Query("FROM CourseEntity where id =?1")
    Optional<CourseEntity> findById(Integer id);

    @Query("FROM CourseEntity ")
    Iterable<CourseEntity> findAll();

    @Transactional
    @Query("DELETE from CourseEntity where id =?1")
    void deleteById(Integer id);

    public Page<CourseEntity> findByPrice(Long price, Pageable pageable);

    public Page<CourseEntity> findByPriceBetween(Long fromPrice, Long toPrice, Pageable pageable);

    void save(CourseEntity entity);
}

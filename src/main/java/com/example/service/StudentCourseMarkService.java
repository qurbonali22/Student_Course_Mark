package com.example.service;

import com.example.dto.StudentCourseMarkDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseMarkEntity;
import com.example.entity.StudentEntity;
import com.example.repository.CourseRepository;
import com.example.repository.StudentCourseMarkRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseMarkService {

    @Autowired
    private StudentCourseMarkRepository repository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

//    1
    public StudentCourseMarkDTO create(StudentCourseMarkDTO dto){

        StudentCourseMarkEntity entity = toEntity(dto);

        if (entity == null) return null;

        repository.save(entity);

        dto.setCourse(entity.getCourse());
        dto.setStudent(entity.getStudent());
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

//    2
    public Boolean update(Integer id, StudentCourseMarkDTO dto){

        Optional<StudentCourseMarkEntity> optional = repository.findById(id);

        if (optional.isEmpty()) return false;

        StudentCourseMarkEntity entity = toEntity(dto);

        if (entity == null) return false;

        repository.save(entity);
        return true;
    }

//    3
    public StudentCourseMarkDTO getById(Integer id){

        Optional<StudentCourseMarkEntity> optional = repository.findById(id);

        if (optional.isEmpty()) return null;

        return toDto1(optional.get());
    }

//    4
    public StudentCourseMarkDTO getByIdFull(Integer id){

        Optional<StudentCourseMarkEntity> optional = repository.findById(id);

        if (optional.isEmpty()) return null;

        return toDto2(optional.get());
    }

//    5
    public Boolean delete(Integer id){

        Optional<StudentCourseMarkEntity> optional = repository.findById(id);

        if (optional.isEmpty()) return false;

        repository.delete(optional.get().getId());
//        repository.deleteById(id);
        return true;
    }

//    6
    public List<StudentCourseMarkDTO> getAll() {

        Iterable<StudentCourseMarkEntity> iterable = repository.findAll();
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : iterable) {
            dtoList.add(toDtoFull(entity));
        }

        return dtoList;
    }

//    7
    public List<StudentCourseMarkDTO> getStudentMarksByDate(Integer studentId, String date){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        if (optional.isEmpty()) return null;

        List<StudentCourseMarkEntity> entities = repository.findByStudentAndCreatedDate(optional.get(), LocalDate.parse(date));
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entities) {
            dtoList.add(toDto2(entity));
        }

        return dtoList;
    }

//    8
    public List<StudentCourseMarkDTO> getStudentMarksByDateBetween(Integer studentId, String dateFrom, String dateTo){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        if (optional.isEmpty()) return null;

        List<StudentCourseMarkEntity> entities = repository.findByStudentAndCreatedDateBetween(optional.get(), LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entities) {
            dtoList.add(toDto2(entity));
        }

        return dtoList;
    }

//    9
    public List<StudentCourseMarkDTO> getStudentMarksOrderDate(Integer studentId){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        if (optional.isEmpty()) return null;

        List<StudentCourseMarkEntity> entities = repository.findByStudentOrderByCreatedDateDesc(optional.get());
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entities) {
            dtoList.add(toDto2(entity));
        }

        return dtoList;
    }

//    10
    public List<StudentCourseMarkDTO> getStudentCourseMarkOrderByDate(Integer studentId, Integer courseId){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        Optional<CourseEntity> optional1 = courseRepository.findById(courseId);

        if (optional.isEmpty() || optional1.isEmpty()) return null;

        List<StudentCourseMarkEntity> entities = repository.findByStudentAndCourseOrderByCreatedDateDesc(optional.get(), optional1.get());
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entities) {
            dtoList.add(toDto2(entity));
        }

        return dtoList;
    }

//    11
    public List<StudentCourseMarkDTO> getStudentLastMark(Integer studentId){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        if (optional.isEmpty()) return null;

        List<StudentCourseMarkEntity> entities = repository.findFirstByStudentOrderByCreatedDateDesc(optional.get());
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entities) {
            dtoList.add(toDto2(entity));
        }

        return dtoList;
    }

//    12
    public List<StudentCourseMarkDTO> getStudentHighMark3(Integer studentId){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        if (optional.isEmpty()) return null;

        List<StudentCourseMarkEntity> entities = repository.findTop3ByStudentOrderByMarkDesc(optional.get());
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entities) {
            dtoList.add(toDto2(entity));
        }

        return dtoList;
    }

//    13
    public List<StudentCourseMarkDTO> getStudentFistMark(Integer studentId){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        if (optional.isEmpty()) return null;

        List<StudentCourseMarkEntity> entities = repository.findFirstByStudentOrderByCreatedDateAsc(optional.get());
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entities) {
            dtoList.add(toDto2(entity));
        }

        return dtoList;
    }

//    14
    public List<StudentCourseMarkDTO> getStudentCourseFirstMark(Integer studentId, Integer courseId){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        Optional<CourseEntity> optional1 = courseRepository.findById(courseId);

        if (optional.isEmpty() || optional1.isEmpty()) return null;

        List<StudentCourseMarkEntity> entities = repository.findFirstByStudentAndCourseOrderByCreatedDateAsc(optional.get(), optional1.get());
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entities) {
            dtoList.add(toDto2(entity));
        }

        return dtoList;
    }

//    15
    public List<StudentCourseMarkDTO> getStudentCourseHighMark(Integer studentId, Integer courseId){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        Optional<CourseEntity> optional1 = courseRepository.findById(courseId);

        if (optional.isEmpty() || optional1.isEmpty()) return null;

        List<StudentCourseMarkEntity> entities = repository.findFirstByStudentAndCourseOrderByMarkDesc(optional.get(), optional1.get());
        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();

        for (StudentCourseMarkEntity entity : entities) {
            dtoList.add(toDto2(entity));
        }

        return dtoList;
    }

//    16
    public Float getStudentAvgMark(Integer studentId){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        if (optional.isEmpty()) return null;

        return repository.findByStudentAvgMark(optional.get());
    }

//    17
    public Float getStudentAvgMarkInCourse(Integer studentId, Integer courseId){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        Optional<CourseEntity> optional1 = courseRepository.findById(courseId);

        if (optional.isEmpty() || optional1.isEmpty()) return null;

        return repository.findByStudentAvgMark(optional.get(), optional1.get());
}

//    18
    public Integer test18(Integer studentId, float mark){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        if (optional.isEmpty()) return null;

        return repository.test18(optional.get(), mark);
    }

//    19
    public Float test19(Integer courseId){

    Optional<CourseEntity> optional = courseRepository.findById(courseId);

    if (optional.isEmpty()) return null;

    return repository.findByCourseMaxMark(optional.get());
}

//    20
    public Float test20(Integer courseId){

        Optional<CourseEntity> optional = courseRepository.findById(courseId);

        if (optional.isEmpty()) return null;

        return repository.test20(optional.get());
    }

//    21
    public Integer test21(Integer courseId){

        Optional<CourseEntity> optional = courseRepository.findById(courseId);

        if (optional.isEmpty()) return null;

        return repository.test21(optional.get());
    }

//    22
    public Page<StudentCourseMarkDTO> pagination(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<StudentCourseMarkEntity> pageObj  = repository.findAll(pageable);

        List<StudentCourseMarkEntity> entities = pageObj.getContent();
        long totalElements = pageObj.getTotalElements();

        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        for (StudentCourseMarkEntity entity : entities) {
            dtoList.add(toDtoFull(entity));
        }

        return new PageImpl<>(dtoList, pageable, totalElements);
    }

//    23
    public Page<StudentCourseMarkDTO> paginationByStudent(int page, int size, Integer studentId){

        Optional<StudentEntity> optional = studentRepository.findById(studentId);

        if (optional.isEmpty()) return null;

        Sort sort = Sort.by(Sort.Direction.ASC, "createdDate");

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<StudentCourseMarkEntity> pageObj  = repository.findByStudent(optional.get(), pageable);

        List<StudentCourseMarkEntity> entities = pageObj.getContent();
        long totalElements = pageObj.getTotalElements();

        List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
        for (StudentCourseMarkEntity entity : entities) {
            dtoList.add(toDtoFull(entity));
        }

        return new PageImpl<>(dtoList, pageable, totalElements);
    }

//    24
public Page<StudentCourseMarkDTO> paginationByCourse(int page, int size, Integer courseId){

    Optional<CourseEntity> optional = courseRepository.findById(courseId);

    if (optional.isEmpty()) return null;

    Sort sort = Sort.by(Sort.Direction.ASC, "createdDate");

    Pageable pageable = PageRequest.of(page, size, sort);
    Page<StudentCourseMarkEntity> pageObj  = repository.findByCourse(optional.get(), pageable);

    List<StudentCourseMarkEntity> entities = pageObj.getContent();
    long totalElements = pageObj.getTotalElements();

    List<StudentCourseMarkDTO> dtoList = new LinkedList<>();
    for (StudentCourseMarkEntity entity : entities) {
        dtoList.add(toDtoFull(entity));
    }

    return new PageImpl<>(dtoList, pageable, totalElements);
}


    public StudentCourseMarkEntity toEntity(StudentCourseMarkDTO dto){

        Optional<StudentEntity> optional = studentRepository.findById(dto.getStudent_id());
        Optional<CourseEntity> optional1 = courseRepository.findById(dto.getCourse_id());

        if (optional.isEmpty() || optional1.isEmpty()) return null;

        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setMark(dto.getMark());
        entity.setCreatedDate(LocalDate.now());
        entity.setCourse(optional1.get());
        entity.setStudent(optional.get());

        return entity;
    }



    public StudentCourseMarkDTO toDto1(StudentCourseMarkEntity entity){

        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();

        dto.setCourse_id(entity.getCourse().getId());
        dto.setStudent_id(entity.getStudent().getId());
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setMark(entity.getMark());

        return dto;
    }

    public StudentCourseMarkDTO toDto2(StudentCourseMarkEntity entity){

        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();

        StudentEntity student = new StudentEntity();
        student.setId(entity.getStudent().getId());
        student.setName(entity.getStudent().getName());
        student.setSurname(entity.getStudent().getSurname());

        CourseEntity course = new CourseEntity();
        course.setId(entity.getCourse().getId());
        course.setName(entity.getCourse().getName());

        dto.setStudent(student);
        dto.setCourse(course);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setMark(entity.getMark());

        return dto;
    }

    public StudentCourseMarkDTO toDtoFull(StudentCourseMarkEntity entity){

        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();

        dto.setStudent(entity.getStudent());
        dto.setCourse(entity.getCourse());
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setMark(entity.getMark());

        return dto;
    }
}

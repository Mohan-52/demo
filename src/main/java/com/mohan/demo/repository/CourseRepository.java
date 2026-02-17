package com.mohan.demo.repository;

import com.mohan.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCourseName(String name);

    @Query("""
            SELECT c FROM Course c JOIN  c.students s WHERE s.id= :studentId
            """)
    List<Course> findCourseByStudentId(@Param("studentId") Long id);

    @Query("""
            SELECT COUNT(c)>0 FROM Course c JOIN c.students s WHERE s.id= :studentId AND c.id= :courseId
            """)
    boolean existsByStdIdAndCourseId(@Param("studentId") Long studentId, @Param("courseId") Long courseId);
}

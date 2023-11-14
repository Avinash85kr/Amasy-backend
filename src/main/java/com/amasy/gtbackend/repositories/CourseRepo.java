package com.amasy.gtbackend.repositories;

import com.amasy.gtbackend.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Integer> {
}

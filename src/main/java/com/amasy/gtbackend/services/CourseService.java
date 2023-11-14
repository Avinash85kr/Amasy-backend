package com.amasy.gtbackend.services;

import com.amasy.gtbackend.payloads.CourseDto;

import java.util.List;

public interface CourseService {
    CourseDto createCourse(CourseDto courseDto);
    CourseDto getCourseById(Integer courseId);
    List<CourseDto> getAllCourses();
    void deleteCourse(Integer courseId);
}

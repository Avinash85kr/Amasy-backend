package com.amasy.gtbackend.services.impl;

import com.amasy.gtbackend.entities.Course;
import com.amasy.gtbackend.exceptions.ResourceNotFoundException;
import com.amasy.gtbackend.payloads.CourseDto;
import com.amasy.gtbackend.repositories.CourseRepo;
import com.amasy.gtbackend.services.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = this.modelMapper.map(courseDto, Course.class);
        Course saveCourse = this.courseRepo.save(course);
        return this.modelMapper.map(saveCourse, CourseDto.class);
    }

    @Override
    public CourseDto getCourseById(Integer courseId) {
        Course getCourse = this.courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        return this.modelMapper.map(getCourse, CourseDto.class);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> allCourses = this.courseRepo.findAll();
        List<CourseDto> getAllCourses = allCourses.stream().map((course) -> this.modelMapper.map(course, CourseDto.class)).collect(Collectors.toList());
        return getAllCourses;
    }

    @Override
    public void deleteCourse(Integer courseId) {
        Course course = this.courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        this.courseRepo.delete(course);
    }
}

package com.amasy.gtbackend.controllers;

import com.amasy.gtbackend.payloads.ApiResponse;
import com.amasy.gtbackend.payloads.CourseDto;
import com.amasy.gtbackend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping("/")
    public ResponseEntity<CourseDto> create(@Valid @RequestBody CourseDto courseDto){
        CourseDto save = this.courseService.createCourse(courseDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Integer courseId){
        CourseDto get = this.courseService.getCourseById(courseId);
        return ResponseEntity.ok(get);
    }
    @GetMapping("/")
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        List<CourseDto> getAll = this.courseService.getAllCourses();
        return ResponseEntity.ok(getAll);
    }
    @DeleteMapping("/{courseId}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable Integer courseId){
        this.courseService.deleteCourse(courseId);
        return new ResponseEntity<>(new ApiResponse("Course deleted successfully", true), HttpStatus.OK);
    }
}

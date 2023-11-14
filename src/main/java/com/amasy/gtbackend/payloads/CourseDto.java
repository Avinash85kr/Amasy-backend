package com.amasy.gtbackend.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CourseDto {
    private int id;
    @NotEmpty
    private String aff;
    @NotEmpty
    private String courseCode;
    @NotEmpty
    private String courseName;
    @NotEmpty
    private String secName;
    @NotEmpty
    private String secCode;
}

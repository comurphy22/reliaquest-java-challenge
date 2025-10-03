package com.challenge.api.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private Instant contractHireDate;
}
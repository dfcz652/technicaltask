package com.example.technicaltask.objects.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateDto {

    private LocalDate fromDate;

    private LocalDate toDate;
}

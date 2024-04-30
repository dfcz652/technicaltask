package com.example.technicaltask.objects.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class UserDto {
    private String id;

    private String email;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String address;

    private String phoneNumber;
}

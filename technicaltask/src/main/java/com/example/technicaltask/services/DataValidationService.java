package com.example.technicaltask.services;

import com.example.technicaltask.objects.dtos.UserDto;

public interface DataValidationService {

    boolean checkEmailValid(UserDto userDto);

    boolean checkBirthDateValid(UserDto userDto);
}

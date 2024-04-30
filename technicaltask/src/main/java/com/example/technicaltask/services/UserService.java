package com.example.technicaltask.services;

import com.example.technicaltask.entities.User;
import com.example.technicaltask.objects.dtos.DateDto;
import com.example.technicaltask.objects.dtos.UserDto;
import com.example.technicaltask.objects.responses.DateResponse;
import com.example.technicaltask.objects.responses.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse checkIsDataValidAndCreate(UserDto userDto, boolean passingAge);

    boolean ageCheck(UserDto userDto);

    String checkIsDataValid(UserDto userDto, boolean passingAge);

    UserResponse updateUserById(UserDto userDto, boolean passingAge);

    void deleteById(UserDto userDto);

    DateResponse getAllByBirthDate(DateDto dateDto);

    String checkValidationDate(DateDto dateDto);

}

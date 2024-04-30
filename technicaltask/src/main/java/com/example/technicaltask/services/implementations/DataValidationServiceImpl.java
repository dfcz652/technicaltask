package com.example.technicaltask.services.implementations;

import com.example.technicaltask.objects.dtos.UserDto;
import com.example.technicaltask.services.DataValidationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DataValidationServiceImpl implements DataValidationService {

    public boolean checkEmailValid(UserDto userDto) {

        return EmailValidator.getInstance().isValid(userDto.getEmail());
    }

    public boolean checkBirthDateValid(UserDto userDto) {

        return userDto.getBirthDate().isBefore(LocalDate.now());
    }

}

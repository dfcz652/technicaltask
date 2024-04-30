package com.example.technicaltask.services;

import com.example.technicaltask.objects.dtos.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataValidationServiceTest {

    @Mock
    private DataValidationService dataValidationService;

    private UserDto userDto;

    @BeforeEach
    void setUp() {

        userDto = new UserDto();
    }

    @Test
    public void testCheckEmailValid_ReturnsTrue_ValidEmail() {

        userDto.setEmail("test@gmail.com");

        when(dataValidationService.checkEmailValid(userDto)).thenReturn(true);

        boolean isValid = dataValidationService.checkEmailValid(userDto);

        assertTrue(isValid);
    }

    @Test
    public void testCheckEmailValid_ReturnsFalse_InvalidEmail() {

        userDto.setEmail("testgmail.com");

        when(dataValidationService.checkEmailValid(userDto)).thenReturn(false);

        boolean isValid = dataValidationService.checkEmailValid(userDto);

        assertFalse(isValid);
    }

    @Test
    public void testCheckBirthDateValid_ReturnsTrue_ValidBirthDate() {

        LocalDate birthDate = LocalDate.parse("2003-11-16");

        userDto.setBirthDate(birthDate);

        when(dataValidationService.checkEmailValid(userDto)).thenReturn(true);

        boolean isValid = dataValidationService.checkEmailValid(userDto);

        assertTrue(isValid);
    }

    @Test
    public void testCheckBirthDateValid_ReturnsFalse_InvalidBirthDate() {

        LocalDate birthDate = LocalDate.parse("2503-11-16");

        userDto.setBirthDate(birthDate);

        when(dataValidationService.checkEmailValid(userDto)).thenReturn(false);

        boolean isValid = dataValidationService.checkEmailValid(userDto);

        assertFalse(isValid);
    }
}

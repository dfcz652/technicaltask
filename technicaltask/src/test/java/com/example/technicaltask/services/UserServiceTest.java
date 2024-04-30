package com.example.technicaltask.services;

import com.example.technicaltask.entities.User;
import com.example.technicaltask.objects.dtos.DateDto;
import com.example.technicaltask.objects.dtos.UserDto;
import com.example.technicaltask.objects.responses.DateResponse;
import com.example.technicaltask.objects.responses.UserResponse;
import com.example.technicaltask.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    public UserService userService;

    @Mock
    public UserRepository userRepository;

    private UserResponse userResponse;

    private UserDto userDto;

    private DateDto dateDto;

    private User user;

    private User updatedUser;

    private LocalDate birthDate;

    private String response;

    @BeforeEach
    void setUp() {

        userDto = new UserDto();

        dateDto = new DateDto();

        user = new User();

        updatedUser = new User();

        response = "";

    }

    @Test
    public void testCheckIsDataValidAndCreate_ValidData_ReturnsSuccessResponse() {

        birthDate = LocalDate.parse("2003-11-16");

        userDto.setEmail("test@email.com");
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setBirthDate(birthDate);
        userDto.setAddress("test");
        userDto.setPhoneNumber("1234567890");

        when(userService.checkIsDataValidAndCreate(userDto, true)).thenReturn(new UserResponse("Success", user));

        UserResponse response = userService.checkIsDataValidAndCreate(userDto, true);

        assertEquals("Success", response.getMessage());
        assertNotNull(response.getUser());

    }

    @Test
    public void testCheckIsDataValidAndCreate_InvalidData_ReturnsErrorResponse() {

        birthDate = LocalDate.parse("2003-11-16");

        userDto.setEmail(null);
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setBirthDate(birthDate);
        userDto.setAddress("test");
        userDto.setPhoneNumber("1234567890");

        when(userService.checkIsDataValidAndCreate(userDto, true)).thenReturn(new UserResponse("You must fill in all required fields", null));

        userResponse = userService.checkIsDataValidAndCreate(userDto, true);

        assertEquals("You must fill in all required fields", userResponse.getMessage());
        assertNull(userResponse.getUser());

    }

    @Test
    public void testAgeCheck_UserOldEnough_ReturnsTrue() {

        birthDate = LocalDate.parse("2003-11-16");

        userDto.setBirthDate(birthDate);

        when(userService.ageCheck(userDto)).thenReturn(true);

        boolean result = userService.ageCheck(userDto);

        assertTrue(result);

    }

    @Test
    public void testAgeCheck_UserTooYoung_ReturnsFalse() {

        birthDate = LocalDate.parse("2016-11-16");

        userDto.setBirthDate(birthDate);

        when(userService.ageCheck(userDto)).thenReturn(false);

        boolean result = userService.ageCheck(userDto);

        assertFalse(result);

    }

    @Test
    public void testCheckIsDataValid_NullField_ReturnsRequiredFieldsError() {

        userDto.setEmail("test@email.com");
        userDto.setFirstName("test");
        userDto.setLastName(null);

        when(userService.checkIsDataValid(userDto, true)).thenReturn("You must fill in all required fields");

        response = userService.checkIsDataValid(userDto, true);

        assertEquals("You must fill in all required fields", response);

    }

    @Test
    public void testCheckIsDataValid_InvalidEmail_ReturnsInvalidEmailError() {

        userDto.setEmail("testemail.com");
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setBirthDate(LocalDate.now());

        when(userService.checkIsDataValid(userDto, true)).thenReturn("Invalid Email:" + " " + userDto.getEmail());

        response = userService.checkIsDataValid(userDto, true);

        assertEquals("Invalid Email: testemail.com", response);

    }

    @Test
    public void testCheckIsDataValid_InvalidBirthDate_ReturnsInvalidBirthDateError() {

        LocalDate birthDate = LocalDate.parse("2516-11-16");

        userDto.setEmail("test@email.com");
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setBirthDate(birthDate);

        when(userService.checkIsDataValid(userDto, true)).thenReturn("Invalid Birth Date:" + " " + userDto.getBirthDate());

        response = userService.checkIsDataValid(userDto, true);

        assertEquals("Invalid Birth Date:" + " " + "2516-11-16", response);

    }

    @Test
    public void testCheckIsDataValid_cannotCreateUserIfYouAreUnder_ReturnsUnderAgeError() {

        int ageForRegistration = 18;

        birthDate = LocalDate.parse("2016-11-16");

        userDto.setEmail("test@email.com");
        userDto.setFirstName("test");
        userDto.setLastName("test");
        userDto.setBirthDate(birthDate);

        when(userService.checkIsDataValid(userDto, false)).thenReturn("You cannot create a user if you are under " + ageForRegistration + " years old");

        response = userService.checkIsDataValid(userDto, false);

        assertEquals("You cannot create a user if you are under " + "18" + " years old", response);

    }

    @Test
    public void testUpdateUserById_ValidUserData_SuccessfullyUpdatesUser() {

        userDto.setId("123321");
        userDto.setEmail("new_test@email.com");
        userDto.setFirstName("new_test");
        userDto.setLastName("new_test");

        updatedUser.setId("123321");
        updatedUser.setEmail("new_test@email.com");
        updatedUser.setFirstName("new_test");
        updatedUser.setLastName("new_test");

        when(userService.updateUserById(userDto, true)).thenReturn(new UserResponse("User data with id " + userDto.getId() + " was updated", updatedUser));

        userResponse = userService.updateUserById(userDto, true);

        assertNotNull(userResponse.getUser());

        assertEquals("User data with id 123321 was updated", userResponse.getMessage());
        assertEquals("new_test@email.com", userResponse.getUser().getEmail());
        assertEquals("new_test", userResponse.getUser().getFirstName());
        assertEquals("new_test", userResponse.getUser().getLastName());

    }

    @Test
    void testDeleteById_SuccessfulDeletion() {

        userDto.setId("123321");

        userRepository.deleteById(userDto.getId());

        verify(userRepository, times(1)).deleteById("123321");

    }

    @Test
    void testGetAllByBirthDate_ValidDates_SuccessfulResponse() {

        dateDto.setFromDate(LocalDate.of(1990, 1, 1));
        dateDto.setToDate(LocalDate.of(2000, 1, 1));

        List<User> users = List.of();

        when(userService.getAllByBirthDate(dateDto)).thenReturn(new DateResponse("Success", users));

        DateResponse dateResponse = userService.getAllByBirthDate(dateDto);

        assertEquals("Success", dateResponse.getMessage());

        assertNotNull(dateResponse.getUsers());

        assertEquals(0, dateResponse.getUsers().size());

    }

    @Test
    void testCheckValidationDate_ValidDates_ReturnsEmptyString() {

        dateDto.setFromDate(LocalDate.of(2022, 1, 1));
        dateDto.setToDate(LocalDate.of(2022, 1, 10));

        when(userService.checkValidationDate(dateDto)).thenReturn("");

        response = userService.checkValidationDate(dateDto);

        assertEquals("", response);

    }

    @Test
    void testCheckValidationDate_InvalidDates_ReturnsErrorMessage() {

        dateDto.setFromDate(LocalDate.of(2022, 1, 10));
        dateDto.setToDate(LocalDate.of(2022, 1, 1));

        when(userService.checkValidationDate(dateDto)).thenReturn("The From date must be less than the To date");

        response = userService.checkValidationDate(dateDto);

        assertEquals("The From date must be less than the To date", response);
    }

    @Test
    void testCheckValidationDate_EqualDates_ReturnsErrorMessage() {

        dateDto.setFromDate(LocalDate.of(2022, 1, 1));
        dateDto.setToDate(LocalDate.of(2022, 1, 1));

        when(userService.checkValidationDate(dateDto)).thenReturn("The From date must be less than the To date");

        response = userService.checkValidationDate(dateDto);

        assertEquals("The From date must be less than the To date", response);

    }

    @Test
    void testCheckValidationDate_SameDates_ReturnsErrorMessage() {

        LocalDate sameDate = LocalDate.of(2022, 1, 1);

        dateDto.setFromDate(sameDate);
        dateDto.setToDate(sameDate);

        when(userService.checkValidationDate(dateDto)).thenReturn("The From date must be less than the To date");

        response = userService.checkValidationDate(dateDto);

        assertEquals("The From date must be less than the To date", response);

    }
}

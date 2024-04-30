package com.example.technicaltask.services.implementations;

import com.example.technicaltask.entities.User;
import com.example.technicaltask.exceptions.Status409UserNotFoundException;
import com.example.technicaltask.objects.dtos.DateDto;
import com.example.technicaltask.objects.dtos.UserDto;
import com.example.technicaltask.objects.responses.DateResponse;
import com.example.technicaltask.objects.responses.UserResponse;
import com.example.technicaltask.repositories.UserRepository;
import com.example.technicaltask.services.DataValidationService;
import com.example.technicaltask.services.UserService;
import lombok.RequiredArgsConstructor;

import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${age.for.registration}")
    private Long ageForRegistration;

    private final UserRepository userRepository;

    private final DataValidationService dataValidationService;

    public UserResponse checkIsDataValidAndCreate(UserDto userDto, boolean passingAge) {

        String response = checkIsDataValid(userDto, passingAge);

        if (!(response.equals("")))
            return new UserResponse(response);

        User user = userRepository.save(User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .birthDate(userDto.getBirthDate())
                .address(userDto.getAddress())
                .phoneNumber(userDto.getPhoneNumber())
                .build());

        return new UserResponse("Success", user);

    }

    public boolean ageCheck (UserDto userDto){

        LocalDate currentDate = LocalDate.now();

        Period period = Period.between(userDto.getBirthDate(), currentDate);

        return period.getYears() >= ageForRegistration;

    }

    public String checkIsDataValid (UserDto userDto,boolean passingAge){

        String response = "";

        if (userDto.getEmail() == null || userDto.getFirstName() == null || userDto.getLastName() == null || userDto.getBirthDate() == null)
            response = "You must fill in all required fields";
        else if (!dataValidationService.checkEmailValid(userDto))
            response = "Invalid Email:" + " " + userDto.getEmail();
        else if (!dataValidationService.checkBirthDateValid(userDto))
            response = "Invalid Birth Date:" + " " + userDto.getBirthDate();
        else if (!passingAge)
            response = "You cannot create a user if you are under " + ageForRegistration + " years old";

        return response;
    }

    @SneakyThrows
    public UserResponse updateUserById (UserDto userDto,boolean passingAge){

        String response = checkIsDataValid(userDto, passingAge);

        if (!(response.equals("")))
            return new UserResponse(response);

        User user = userRepository.findUserById(userDto.getId()).orElseThrow(() -> new Status409UserNotFoundException(userDto.getId()));

        BeanUtils.copyProperties(userDto, user);

        userRepository.save(user);

        return new UserResponse("User data with id " + userDto.getId() + " was updated", user);
    }

    @SneakyThrows
    public void deleteById(UserDto userDto) {

        userRepository.findUserById(userDto.getId()).orElseThrow(() -> new Status409UserNotFoundException(userDto.getId()));

        userRepository.deleteById(userDto.getId());
    }

    @SneakyThrows
    public DateResponse getAllByBirthDate(DateDto dateDto) {

        String response = checkValidationDate(dateDto);

        if (!(response.equals("")))
            return new DateResponse(response);

        return new DateResponse("Success", userRepository.findByBirthDateBetween(
                dateDto.getFromDate(), dateDto.getToDate()).orElseThrow(Status409UserNotFoundException::new));

    }

    public String checkValidationDate(DateDto dateDto) {

        String response = "";

        if (!dateDto.getFromDate().isBefore(dateDto.getToDate()))
            response = "The From date must be less than the To date";

        return response;
    }

}

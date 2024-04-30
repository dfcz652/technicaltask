package com.example.technicaltask.controllers.implementations;

import com.example.technicaltask.controllers.UserControllerSpec;
import com.example.technicaltask.objects.dtos.DateDto;
import com.example.technicaltask.objects.dtos.UserDto;
import com.example.technicaltask.objects.responses.DateResponse;
import com.example.technicaltask.objects.responses.UserResponse;
import com.example.technicaltask.services.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController implements UserControllerSpec {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody UserDto userDto) {

        boolean passingAge = userService.ageCheck(userDto);

        UserResponse response = userService.checkIsDataValidAndCreate(userDto, passingAge);

        return ResponseEntity.ok(response);
    }

    @SneakyThrows
    @PostMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UserDto userDto) {

        boolean passingAge = userService.ageCheck(userDto);

        return ResponseEntity.ok(userService.updateUserById(userDto, passingAge));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody UserDto userDto) {

        userService.deleteById(userDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<DateResponse> getAll(@RequestBody DateDto dateDto) {

        return ResponseEntity.ok(userService.getAllByBirthDate(dateDto));
    }

}

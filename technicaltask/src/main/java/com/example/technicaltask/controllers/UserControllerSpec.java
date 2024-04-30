package com.example.technicaltask.controllers;

import com.example.technicaltask.objects.dtos.DateDto;
import com.example.technicaltask.objects.dtos.UserDto;
import com.example.technicaltask.objects.responses.DateResponse;
import com.example.technicaltask.objects.responses.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserControllerSpec {

    ResponseEntity<UserResponse> create(@RequestBody UserDto userDto);

    ResponseEntity<UserResponse> update(@RequestBody UserDto userDto);

    ResponseEntity<?> delete(@RequestBody UserDto userDto);

    ResponseEntity<DateResponse> getAll(@RequestBody DateDto dateDto);
}

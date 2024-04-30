package com.example.technicaltask.objects.responses;

import com.example.technicaltask.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DateResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<User> users;
}

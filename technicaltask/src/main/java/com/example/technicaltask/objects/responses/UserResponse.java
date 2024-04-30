package com.example.technicaltask.objects.responses;

import com.example.technicaltask.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private User user;

}

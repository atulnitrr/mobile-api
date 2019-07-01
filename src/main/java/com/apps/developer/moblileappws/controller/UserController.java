package com.apps.developer.moblileappws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apps.developer.moblileappws.modal.request.UserRequest;
import com.apps.developer.moblileappws.modal.response.UserResponse;


@RestController
@RequestMapping("/users")
public class UserController {


    @GetMapping
    public String getUser() {
        return "Get user called";
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody final UserRequest userRequest) {
        final UserResponse userResponse = new UserResponse();
        userResponse.setEmail(userRequest.getEmail());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping
    public String updateUser() {
        return "Update user was called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "Delete user was called";
    }
}

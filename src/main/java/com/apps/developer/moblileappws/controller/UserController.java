package com.apps.developer.moblileappws.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apps.developer.moblileappws.dto.UserDto;
import com.apps.developer.moblileappws.model.request.UserRequest;
import com.apps.developer.moblileappws.model.response.UserResponse;
import com.apps.developer.moblileappws.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable final String userId) {

        final UserResponse userResponse = new UserResponse();
        final UserDto userDto = userService.getUserByUserID(userId);
        BeanUtils.copyProperties(userDto, userResponse);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody final UserRequest userRequest) {
        final UserResponse userResponse = new UserResponse();
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        final UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, userResponse);
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

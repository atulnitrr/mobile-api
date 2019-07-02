package com.apps.developer.moblileappws.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.apps.developer.moblileappws.dto.UserDto;


public interface UserService extends UserDetailsService {

    UserDto createUser(final UserDto userDto);
}

package com.apps.developer.moblileappws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.apps.developer.moblileappws.dto.UserDto;
import com.apps.developer.moblileappws.entity.UserEntity;
import com.apps.developer.moblileappws.repository.UserRepository;
import com.apps.developer.moblileappws.service.UserService;
import com.apps.developer.moblileappws.utils.Utils;


@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override public UserDto createUser(final UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()) != null)
            throw new RuntimeException("Record already exist");

        final UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setUserId(utils.generateUserId(30));



        final UserEntity storedUser = userRepository.save(userEntity);
        final UserDto returnUseDto = new UserDto();
        BeanUtils.copyProperties(storedUser, returnUseDto);
        return returnUseDto;
    }
}

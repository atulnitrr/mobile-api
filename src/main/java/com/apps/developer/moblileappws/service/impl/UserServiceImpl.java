package com.apps.developer.moblileappws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apps.developer.moblileappws.dto.UserDto;
import com.apps.developer.moblileappws.entity.UserEntity;
import com.apps.developer.moblileappws.repository.UserRepository;
import com.apps.developer.moblileappws.service.UserService;


@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override public UserDto createUser(final UserDto userDto) {
        final UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setEncryptedPassword("test");
        userEntity.setUserId("test-userId");
        final UserEntity storedUser = userRepository.save(userEntity);
        final UserDto returnUseDto = new UserDto();
        BeanUtils.copyProperties(storedUser, returnUseDto);
        return returnUseDto;
    }
}

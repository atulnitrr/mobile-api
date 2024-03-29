package com.apps.developer.moblileappws.service.impl;

import java.util.Collections;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {

            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), Collections.emptyList());
    }

    @Override public UserDto getUser(final String email) {
        final UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("user not found ");
        }
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }

    @Override public UserDto getUserByUserID(final String userId) {
        final UserDto userDto = new UserDto();
        final UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw new UsernameNotFoundException(userId);
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }
}

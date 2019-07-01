package com.apps.developer.moblileappws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.apps.developer.moblileappws.entity.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(final String email);
}

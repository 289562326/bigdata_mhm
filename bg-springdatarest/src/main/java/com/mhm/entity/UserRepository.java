package com.mhm.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by MaHuiming on 2019/6/12.
 */
@RepositoryRestResource(path="user")
public interface  UserRepository extends JpaRepository<User, Long> {
}

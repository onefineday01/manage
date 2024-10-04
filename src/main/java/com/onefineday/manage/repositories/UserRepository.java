package com.onefineday.manage.repositories;

import com.onefineday.manage.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
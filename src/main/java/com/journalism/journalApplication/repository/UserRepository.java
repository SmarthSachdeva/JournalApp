package com.journalism.journalApplication.repository;

import com.journalism.journalApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUserName(String userName);

}

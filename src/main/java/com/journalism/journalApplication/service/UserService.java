package com.journalism.journalApplication.service;

import com.journalism.journalApplication.entity.User;
import com.journalism.journalApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {


    @Autowired
    private UserRepository userRep;

    public List<User> getAllUsers() {

        return userRep.findAll();
    }
    public void saveEntry(User user) {

        userRep.save(user);
    }
    public User getUserById(Long id){
        return userRep.findById(id).get();
    }

    public void deleteUSerById(Long id) {
        userRep.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRep.findByUserName(userName);
    }
}

package com.journalism.journalApplication.controller;

import com.journalism.journalApplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.journalism.journalApplication.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/adduser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.saveEntry(user);
        return new ResponseEntity<>(user , HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteuser/{tempId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long tempId) {
        User user = userService.getUserById(tempId);
        userService.deleteUSerById(tempId);
        return new ResponseEntity<>(user , HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable String userName){
        User existingUser = userService.findByUserName(userName);
        if(existingUser != null){
            existingUser.setUserName(user.getUserName());
            existingUser.setPassword(user.getPassword());
            userService.saveEntry((existingUser));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



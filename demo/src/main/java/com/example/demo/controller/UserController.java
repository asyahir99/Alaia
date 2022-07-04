package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String hello() {
		return "hello";
    }

    //Authenticate
    @RequestMapping(value="/login/{phoneNum}/{password}", method=RequestMethod.GET)
    public User authenticate(@PathVariable(value = "phoneNum") String phoneNum, @PathVariable(value = "password") String password) {
		return userService.Authenticate(phoneNum, password);
    }
    
    //Create User
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public User createUser(@RequestBody User user) {

        return userService.createUser(user);
    }

    //Get All User
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }
    
    //Get User By ID
    @RequestMapping(value="/user/{userID}", method=RequestMethod.GET)
    public User getUser(@PathVariable(value = "userID") Long userID) {
        return userService.getUser(userID);
    }

    //Update user
    @RequestMapping(value="/user/{userID}", method=RequestMethod.PUT)
    public User updateUser(@PathVariable(value = "userID") Long userID, @RequestBody User userDetails) {
        return userService.updateUser(userID, userDetails);
    }

    //Delete User
    @RequestMapping(value="/user/{userID}", method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value = "userID") Long userID) {
    	userService.deleteUser(userID);
    }
}

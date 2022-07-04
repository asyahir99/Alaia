package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	
    @Autowired
    UserRepository userRepository;
    
    private Boolean checkPassword(User user, String password)
    {
    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	
    	return bCryptPasswordEncoder.matches(password, user.getPassword());
    }
    
    private void hashPassword(User user)
    {
    	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }
    
    // CREATE 
    public User createUser(User user) {
    	hashPassword(user);
        return userRepository.save(user);
    }

    // READ
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    public User getUser(Long userID) {
        return userRepository.findById(userID).get();
    }
    
    public User Authenticate(String phoneNum, String password)
    {
    	User user = userRepository.findByPhoneNum(phoneNum);
    	
    	if(checkPassword(user, password)) {
            return user;
    	}
    	else {
    		return null;
    	}
    }
    
    // UPDATE
    public User updateUser(Long userID, User userDetails) {
		User user = userRepository.findById(userID).get();
		
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		user.setPhoneNum(userDetails.getPhoneNum());
        
        return userRepository.save(user);                                
    }

    // DELETE
    public void deleteUser(Long userID) {
    	userRepository.deleteById(userID);
    }

}

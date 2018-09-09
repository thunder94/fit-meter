package pl.jakub.fitmeter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.jakub.fitmeter.model.User;
import pl.jakub.fitmeter.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
    @Autowired
    public UserService(UserRepository userRepository) { 
    	this.userRepository = userRepository;
    }
    
    public User findById(Long id) {
    	return userRepository.findById(id).orElse(null);
    }
    
    public User findByUsername(String username) {
    	return userRepository.findByUsername(username);
    }
    
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
}

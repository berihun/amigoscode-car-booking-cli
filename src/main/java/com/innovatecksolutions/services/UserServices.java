package com.innovatecksolutions.services;

import com.innovatecksolutions.model.Car;
import com.innovatecksolutions.model.User;
import com.innovatecksolutions.repository.UsersRepository;

import java.util.List;


public class UserServices {
    private final UsersRepository usersRepository;
    public UserServices(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public List<User> findAllUsers(){
        return usersRepository.findAllUsers();
    }
    public void save(User user){
        usersRepository.save(user);
    }
    public void delete(User user){
        usersRepository.delete(user);
    }
    public void update(User user){
        usersRepository.update(user);
    }

}

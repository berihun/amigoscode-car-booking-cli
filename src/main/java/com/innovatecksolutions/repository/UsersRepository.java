package com.innovatecksolutions.repository;

import com.innovatecksolutions.model.User;

import java.util.List;

public interface UsersRepository {

    void save(User user);
    void update(User user);
    void delete(User user);
    List<User> findAllUsers();
}

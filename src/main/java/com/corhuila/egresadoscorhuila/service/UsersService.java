package com.corhuila.egresadoscorhuila.service;

import com.corhuila.egresadoscorhuila.entity.Users;

import java.util.List;

public interface UsersService {

    List<Users> findAll();

    Users findById(Long userId);

    Users createUser(Users request);

    void deleteUser(Long userId);

    Boolean login(String request);

}

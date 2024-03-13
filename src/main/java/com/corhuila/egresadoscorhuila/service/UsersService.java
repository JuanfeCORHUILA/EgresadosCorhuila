package com.corhuila.egresadoscorhuila.service;

import com.corhuila.egresadoscorhuila.entity.Users;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import org.apache.coyote.Response;

import java.util.List;

public interface UsersService {

    ResponseGeneric findAll();

    Users findById(Long userId);

    Users createUser(Users request);

    void deleteUser(Long userId);

    Boolean login(String request);

}

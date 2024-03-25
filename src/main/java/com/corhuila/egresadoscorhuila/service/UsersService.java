package com.corhuila.egresadoscorhuila.service;

import com.corhuila.egresadoscorhuila.dto.CreateUserDto;
import com.corhuila.egresadoscorhuila.dto.JwtTokenDto;
import com.corhuila.egresadoscorhuila.dto.LoginUserDto;
import com.corhuila.egresadoscorhuila.entity.CreateUsers;
import com.corhuila.egresadoscorhuila.entity.Users;
import com.corhuila.egresadoscorhuila.exceptions.AttributeException;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import org.apache.coyote.Response;

import javax.naming.directory.AttributeInUseException;
import java.util.List;


public interface UsersService {

    ResponseGeneric findAll();

    Users findById(Long userId);

    Users createUser(Users request);

    void deleteUser(Long userId);

    CreateUsers create(CreateUserDto createUserDto) throws AttributeException;

    JwtTokenDto login (LoginUserDto loginUserDto);

}

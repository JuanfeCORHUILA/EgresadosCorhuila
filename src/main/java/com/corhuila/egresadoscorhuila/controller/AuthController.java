package com.corhuila.egresadoscorhuila.controller;

import com.corhuila.egresadoscorhuila.dto.CreateUserDto;
import com.corhuila.egresadoscorhuila.dto.JwtTokenDto;
import com.corhuila.egresadoscorhuila.dto.LoginUserDto;
import com.corhuila.egresadoscorhuila.entity.CreateUsers;
import com.corhuila.egresadoscorhuila.entity.Users;
import com.corhuila.egresadoscorhuila.exceptions.AttributeException;
import com.corhuila.egresadoscorhuila.service.UsersService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.AttributeInUseException;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    UsersService usersService;

    @PostMapping("/create")
    public ResponseEntity<CreateUsers> create(@Valid @RequestBody CreateUserDto createUserDto) throws AttributeException {
       return new ResponseEntity<>(usersService.create(createUserDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@Valid @RequestBody LoginUserDto loginUserDto) {
        return new ResponseEntity<>(usersService.login(loginUserDto), HttpStatus.OK);
    }

    @PostMapping(path = "/preRegistro", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users createUser(@RequestBody @Validated Users request) {
        return usersService.createUser(request);
    }

}

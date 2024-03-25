package com.corhuila.egresadoscorhuila.controller;

import com.corhuila.egresadoscorhuila.dto.CreateUserDto;
import com.corhuila.egresadoscorhuila.dto.JwtTokenDto;
import com.corhuila.egresadoscorhuila.dto.LoginUserDto;
import com.corhuila.egresadoscorhuila.entity.CreateUsers;
import com.corhuila.egresadoscorhuila.exceptions.AttributeException;
import com.corhuila.egresadoscorhuila.service.UsersService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.AttributeInUseException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
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
}

package com.corhuila.egresadoscorhuila.controller;

import com.corhuila.egresadoscorhuila.entity.Users;
import com.corhuila.egresadoscorhuila.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/egresados")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Users> findAll() {
        return usersService.findAll();
    }

    @PostMapping(path = "/recuperarUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users findById(@RequestParam(name = "id") Long userId) {
        return usersService.findById(userId);
    }

    @PostMapping(path = "/crearUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users createUser(@RequestBody @Validated Users request) {
        return usersService.createUser(request);
    }

    @PostMapping(path = "/borrarUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@RequestParam(name = "id") Long userId) {
        usersService.deleteUser(userId);
    }

    @PostMapping(path = "/ingresar", produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean login(@RequestBody String request) {
        return usersService.login(request);
    }

}

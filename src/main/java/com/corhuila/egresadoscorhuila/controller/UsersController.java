package com.corhuila.egresadoscorhuila.controller;

import com.corhuila.egresadoscorhuila.entity.Users;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.corhuila.egresadoscorhuila.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/egresados")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> findAll() {
        return new ResponseEntity<>(usersService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROL_ADMIN', 'ROL_EGRESADO')")
    @PostMapping(path = "/recuperarUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users findById(@RequestParam(name = "id") Long userId) {
        return usersService.findById(userId);
    }

    @PreAuthorize("hasAnyAuthority('ROL_ADMIN', 'ROL_EGRESADO')")
    @PostMapping(path = "/recuperarUsuarioDoc", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users findByDoc(@RequestParam(name = "doc") Long doc, @RequestParam(name = "foto") Boolean foto) {
        return usersService.findByDoc(doc, foto);
    }

    @PreAuthorize("hasAnyAuthority('ROL_ADMIN', 'ROL_EGRESADO')")
    @PostMapping(path = "/actualizarUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users updateUser(@RequestParam(name = "doc") Long doc, @RequestBody @Validated Users users) {
        return usersService.updateUser(users,doc);
    }

    @PreAuthorize("hasAuthority('ROL_EGRESADO')")
    @PostMapping(path = "/crearUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public Users createUser(@RequestBody @Validated Users request) {
        return usersService.createUser(request);
    }

    @PreAuthorize("hasAnyAuthority('ROL_ADMIN', 'ROL_EGRESADO')")
    @PostMapping(path = "/borrarUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@RequestParam(name = "id") Long userId) {
        usersService.deleteUser(userId);
    }



}

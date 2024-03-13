package com.corhuila.egresadoscorhuila.service.impl;

import com.corhuila.egresadoscorhuila.entity.Users;
import com.corhuila.egresadoscorhuila.repository.UserRepository;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.corhuila.egresadoscorhuila.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private UserRepository userRepository;

    @Override
    public ResponseGeneric findAll() {

        try {
            List<Users> users = userRepository.findAll();
            if (users.isEmpty() || users==null){
                return ResponseGeneric.builder()
                        .codResponse(204)
                        .message("Listado de usuarios consultado exitosamente, sin ningun contenido")
                        .status("NOT CONTENT")
                        .build();
            }
            return ResponseGeneric.builder()
                    .codResponse(200)
                    .message("Listado de usuarios consultado exitosamente")
                    .status("OK")
                    .listObject(Collections.singletonList(users))
                    .build();
        }catch (Exception e){
            return ResponseGeneric.builder().codResponse(400).message(e.getMessage()).status("BAD REQUEST").build();
        }

    }

    @Override
    public Users findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("No se encontro el egresado"));
    }

    @Override
    public Users createUser(Users request) {
        return userRepository.save(request);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Boolean login(String request) {
        log.info("request " + request);
        String[] data = request.split("\\|");
        log.info("data: " + data[0] + " - " + data[1]);
        BigInteger noIdentificacion = new BigInteger(data[0]);
        String password = data[1];
        Users user = userRepository.findByNoIdentificacionAndPassword(noIdentificacion, password);
        if (user.getRol() == "admin"){
            return Boolean.TRUE;
        }
        return Boolean.TRUE;
    }
}

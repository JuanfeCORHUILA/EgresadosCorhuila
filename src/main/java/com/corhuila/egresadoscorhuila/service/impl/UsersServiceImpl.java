package com.corhuila.egresadoscorhuila.service.impl;

import com.corhuila.egresadoscorhuila.entity.Users;
import com.corhuila.egresadoscorhuila.repository.UserRepository;
import com.corhuila.egresadoscorhuila.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private UserRepository userRepository;

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
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

package com.corhuila.egresadoscorhuila.service.impl;

import com.corhuila.egresadoscorhuila.dto.CreateUserDto;
import com.corhuila.egresadoscorhuila.entity.CreateUsers;
import com.corhuila.egresadoscorhuila.entity.Users;
import com.corhuila.egresadoscorhuila.enums.RolEnum;
import com.corhuila.egresadoscorhuila.exceptions.AttributeException;
import com.corhuila.egresadoscorhuila.repository.CreateUserRepository;
import com.corhuila.egresadoscorhuila.repository.UserRepository;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.corhuila.egresadoscorhuila.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.naming.directory.AttributeInUseException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private UserRepository userRepository;

    private CreateUserRepository createUserRepository;

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
    public CreateUsers create(CreateUserDto createUserDto) throws AttributeException {

        if (createUserRepository.existsByNoIdentificacion(createUserDto.getNoIdentificacion())){
            throw new AttributeException("El usuario ya existe");
        }

        if (createUserRepository.existsByEmailInstitucional(createUserDto.getEmailInstitucional())){
            throw new AttributeException("El usuario ya existe");
        }

        List<RolEnum> roles =
                createUserDto.getRol().stream().map(rol -> RolEnum.valueOf(rol)).collect(Collectors.toList());

        CreateUsers users = new CreateUsers(createUserDto.getNoIdentificacion(), createUserDto.getEmailInstitucional(), createUserDto.getPassword(), roles);

        return createUserRepository.save(users);
    }
}

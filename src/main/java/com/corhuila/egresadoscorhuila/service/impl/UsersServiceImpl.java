package com.corhuila.egresadoscorhuila.service.impl;

import com.corhuila.egresadoscorhuila.dto.CreateUserDto;
import com.corhuila.egresadoscorhuila.dto.JwtTokenDto;
import com.corhuila.egresadoscorhuila.dto.LoginUserDto;
import com.corhuila.egresadoscorhuila.entity.CreateUsers;
import com.corhuila.egresadoscorhuila.entity.Users;
import com.corhuila.egresadoscorhuila.enums.RolEnum;
import com.corhuila.egresadoscorhuila.exceptions.AttributeException;
import com.corhuila.egresadoscorhuila.jwt.JwtProvider;
import com.corhuila.egresadoscorhuila.repository.CreateUserRepository;
import com.corhuila.egresadoscorhuila.repository.UserRepository;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.corhuila.egresadoscorhuila.service.UsersService;
import com.corhuila.egresadoscorhuila.utils.Operations;
import com.corhuila.egresadoscorhuila.utils.SendEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CreateUserRepository createUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ModelMapper modelMapper;

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
    public Users findByDoc(Long document) {
        try {
            return  userRepository.findByNoIdentificacion(document);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public Users createUser(Users request) {
        int id = Operations.autoIncrement(userRepository.findAll());
        request.setId(id);
        return userRepository.save(request);
    }

    @Override
    public Users updateUser(Users updateUser, Long doc) {
        Users existUser = userRepository.findByNoIdentificacion(doc);
        if (existUser != null){
            modelMapper.map(updateUser, existUser);
            return userRepository.save(existUser);
        }else {
            throw new RuntimeException("Usuario no encontrado con el Dcoumento: "+ doc);
        }
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

        int id = Operations.autoIncrement(createUserRepository.findAll());

        String password = passwordEncoder.encode(createUserDto.getPassword());

        List<RolEnum> roles =
                createUserDto.getRol().stream().map(rol -> RolEnum.valueOf(rol)).collect(Collectors.toList());

        CreateUsers users = new CreateUsers(id,createUserDto.getNoIdentificacion(), createUserDto.getEmailInstitucional(), password, roles);

        return createUserRepository.save(users);
    }

    @Override
    public JwtTokenDto login(LoginUserDto loginUserDto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getNoIdentificacion().toString(), loginUserDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new JwtTokenDto(token);
    }
}

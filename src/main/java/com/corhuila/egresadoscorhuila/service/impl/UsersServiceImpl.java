package com.corhuila.egresadoscorhuila.service.impl;

import com.corhuila.egresadoscorhuila.dto.CreateUserDto;
import com.corhuila.egresadoscorhuila.dto.JwtTokenDto;
import com.corhuila.egresadoscorhuila.dto.LoginUserDto;
import com.corhuila.egresadoscorhuila.dto.UpdatePassword;
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
import java.util.NoSuchElementException;
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
    public Users findByDoc(Long document, Boolean foto) {
        try {
            Users user;

            if (!foto) {
                user = userRepository.findByNoIdentificacionWithoutFotoPerfil(document);
            } else {
                user = userRepository.findByNoIdentificacion(document);
            }

            if (user == null) {
                throw new NoSuchElementException("Usuario no encontrado con el documento: " + document);
            }

            return user;

        } catch (Exception e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
            return null;
        }
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

        if (existUser != null) {
            existUser.setNoIdentificacion(updateUser.getNoIdentificacion());
            existUser.setEmailInstitucional(updateUser.getEmailInstitucional());
            existUser.setTipoDocumento(updateUser.getTipoDocumento());
            existUser.setPrimerNombre(updateUser.getPrimerNombre());
            existUser.setSegundoNombre(updateUser.getSegundoNombre());
            existUser.setPrimerApellido(updateUser.getPrimerApellido());
            existUser.setSegundoApellido(updateUser.getSegundoApellido());
            existUser.setTelefono(updateUser.getTelefono());
            existUser.setEmail(updateUser.getEmail());
            existUser.setEdad(updateUser.getEdad());
            existUser.setFechaNacimiento(updateUser.getFechaNacimiento());
            existUser.setPrograma(updateUser.getPrograma());
            existUser.setCiudadRecidencia(updateUser.getCiudadRecidencia());
            existUser.setDireccionRecidencia(updateUser.getDireccionRecidencia());
            existUser.setSedeUniversitaria(updateUser.getSedeUniversitaria());
            existUser.setUltimoNivelFormacion(updateUser.getUltimoNivelFormacion());
            existUser.setFacultad(updateUser.getFacultad());
            existUser.setUltimoSemestre(updateUser.getUltimoSemestre());
            existUser.setGraduado(updateUser.getGraduado());
            existUser.setFechaGrado(updateUser.getFechaGrado());
            existUser.setModalidad(updateUser.getModalidad());
            existUser.setCalificacionObtenida(updateUser.getCalificacionObtenida());
            existUser.setTituloTrabajoGrado(updateUser.getTituloTrabajoGrado());
            existUser.setTituloObtenido(updateUser.getTituloObtenido());
            existUser.setLabora(updateUser.getLabora());
            existUser.setNombreEmpresa(updateUser.getNombreEmpresa());
            existUser.setRolEjecuta(updateUser.getRolEjecuta());
            existUser.setFechaIngreso(updateUser.getFechaIngreso());
            existUser.setActividadEjecuta(updateUser.getActividadEjecuta());
            existUser.setRangoSalarial(updateUser.getRangoSalarial());
            existUser.setTipoContrato(updateUser.getTipoContrato());
            existUser.setModalidadTrabajo(updateUser.getModalidadTrabajo());
            existUser.setRelacionFormacion(updateUser.getRelacionFormacion());

            if (updateUser.getFotoPerfil() != null) {
                existUser.setFotoPerfil(updateUser.getFotoPerfil());
            }

            return userRepository.save(existUser);
        } else {
            throw new RuntimeException("Usuario no encontrado con el documento: " + doc);
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

    @Override
    public CreateUsers updatePassword(UpdatePassword updatePassword) {
        CreateUsers createUsers = createUserRepository.findByNoIdentificacion(updatePassword.getNoIdentificacion());
        createUsers.setEmailInstitucional(updatePassword.getEmail());
        createUsers.setPassword(passwordEncoder.encode(updatePassword.getPassword()));
        createUserRepository.save(createUsers);
        createUsers.setPassword(null);
        return createUsers;
    }

    @Override
    public CreateUsers findByNumeroDoc(Long numDoc) {

        try {
            CreateUsers createUsers = createUserRepository.findByNoIdentificacion(numDoc);
            createUsers.setPassword(null);
            return createUsers;
        }catch (Exception e){
            throw new RuntimeException("Usuario no encontrado con el documento: " + numDoc);
        }
    }
}

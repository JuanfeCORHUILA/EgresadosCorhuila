package com.corhuila.egresadoscorhuila.service.impl;

import com.corhuila.egresadoscorhuila.entity.Users;
import com.corhuila.egresadoscorhuila.repository.UserRepository;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.corhuila.egresadoscorhuila.service.TotalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TotalEgresadosServiceImpl implements TotalUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseGeneric totalEgresados(String sede) {

        try {
            Long totalEngresadosSede = (long) userRepository.findBySedeUniversitaria(sede).size();
            return ResponseGeneric.builder().codResponse(200).message("Total de egresados por sede consultados exitosamente").status("OK").object(totalEngresadosSede).build();
        }catch (Exception e){
            return ResponseGeneric.builder().codResponse(400).message(e.getMessage()).status("BAD REQUEST").build();
        }
    }

    @Override
    public ResponseGeneric totalEgresadosAños() {
        try {
            List<Users> egresados = userRepository.findByGraduadoTrue();

            // Si no hay egresados, devolver respuesta con código 204
            if (egresados.isEmpty()) {
                return ResponseGeneric.builder()
                        .status("Not Content")
                        .message("No hay egresados para mostrar")
                        .codResponse(204)
                        .build();
            }

            Map<Integer, Long> egresadosPorAño = egresados.stream()
                    .filter(egresado -> egresado.getFechaGrado() != null)
                    .collect(Collectors.groupingBy(
                            egresado -> getYearFromDate(egresado.getFechaGrado()),
                            Collectors.counting()
                    ));


            return ResponseGeneric.builder()
                    .status("Ok")
                    .message("Total de egresados por año listados exitosamente")
                    .codResponse(200)
                    .listObject(Collections.singletonList(egresadosPorAño))
                    .build();

        } catch (Exception e) {
            return ResponseGeneric.builder()
                    .status("Bad Request")
                    .message(e.getMessage())
                    .codResponse(404)
                    .build();
        }
    }

    @Override
    public ResponseGeneric ultimosUsuarios() {
        try {
            List<Users> usuarios = userRepository.findTop5ByOrderByIdDesc();

            if (usuarios.isEmpty()) {
                return ResponseGeneric.builder()
                        .status("Not Content")
                        .message("No hay usuarios para mostrar")
                        .codResponse(204)
                        .build();
            }

            List<Object> nombresApellidos = usuarios.stream()
                    .map(usuario -> usuario.getPrimerNombre() + " " +
                            (usuario.getSegundoNombre() != null ? usuario.getSegundoNombre() + " " : "") +
                            usuario.getPrimerApellido() + " " +
                            (usuario.getSegundoApellido() != null ? usuario.getSegundoApellido() : ""))
                    .collect(Collectors.toList());

            return ResponseGeneric.builder()
                    .status("Ok")
                    .message("Últimos 5 usuarios listados exitosamente")
                    .codResponse(200)
                    .listObject(nombresApellidos)
                    .build();

        } catch (Exception e) {
            return ResponseGeneric.builder()
                    .status("Bad Request")
                    .message(e.getMessage())
                    .codResponse(404)
                    .build();
        }
    }

    private int getYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
}

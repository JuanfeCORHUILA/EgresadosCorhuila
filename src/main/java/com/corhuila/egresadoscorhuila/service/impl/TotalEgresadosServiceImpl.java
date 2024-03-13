package com.corhuila.egresadoscorhuila.service.impl;

import com.corhuila.egresadoscorhuila.repository.UserRepository;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.corhuila.egresadoscorhuila.service.TotalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

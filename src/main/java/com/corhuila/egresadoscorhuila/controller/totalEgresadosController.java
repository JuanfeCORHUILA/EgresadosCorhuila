package com.corhuila.egresadoscorhuila.controller;

import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.corhuila.egresadoscorhuila.service.TotalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/egresados")
@CrossOrigin(origins = "http://localhost:4200")
public class totalEgresadosController {

    @Autowired
    private TotalUserService totalUserService;

    @GetMapping(path = "/totalEgresadosSede", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> totalEgresadosSede(@RequestParam(name = "sede") String sede){
        return new ResponseEntity<>(totalUserService.totalEgresados(sede), HttpStatus.OK);
    }
}

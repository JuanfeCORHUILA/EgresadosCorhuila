package com.corhuila.egresadoscorhuila.controller;

import com.corhuila.egresadoscorhuila.service.GenerateCsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/egresados")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class GenerateCsvController {

    @Autowired
    GenerateCsvService generateCsvService;

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @GetMapping(path = "/export")
    public ResponseEntity<byte[]> exportCsv() {
       String csvString = generateCsvService.generateCsv();
       byte[] buf = csvString.getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ListaEgresado.csv");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return new ResponseEntity<>(buf,headers,HttpStatus.OK);
    }
}

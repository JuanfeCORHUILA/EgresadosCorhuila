package com.corhuila.egresadoscorhuila.controller;

import com.corhuila.egresadoscorhuila.service.GenerateCsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/egresados")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class GenerateCsvController {

    @Autowired
    GenerateCsvService generateCsvService;

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping(path = "/export")
    public ResponseEntity<byte[]> exportCsv(@RequestBody(required = false) List<Long> ids) {
       String csvString = generateCsvService.generateCsv(ids);
       byte[] buf = csvString.getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ListaEgresado.csv");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return new ResponseEntity<>(buf,headers,HttpStatus.OK);
    }
}

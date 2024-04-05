package com.corhuila.egresadoscorhuila.controller;

import com.corhuila.egresadoscorhuila.dto.SenMailRequestDto;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.corhuila.egresadoscorhuila.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/egresados")
@CrossOrigin(origins = "http://localhost:4200")
public class SendMailControler {

    @Autowired
    SendMailService sendMailService;

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping(path = "/sendMail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> sendMail(@Valid @RequestBody SenMailRequestDto senMailRequestDto) throws MessagingException, IOException {

        return new ResponseEntity<>(sendMailService.sendMassiveMail(senMailRequestDto), HttpStatus.OK);
    }
}

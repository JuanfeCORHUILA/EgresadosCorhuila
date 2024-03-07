package com.corhuila.egresadoscorhuila.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    /**
     * Método encargado de manejar las excepciones de validaciones
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> argumentNotValid(MethodArgumentNotValidException e) {
        List<FieldError> errorsFields = e.getFieldErrors();
        List<String> errors = new ArrayList<>();
        for (FieldError error : errorsFields) {
            log.error("Error en el campo: {} - {}", error.getField(), error.getDefaultMessage());
            errors.add("El atributo " + error.getField() + " " + error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}

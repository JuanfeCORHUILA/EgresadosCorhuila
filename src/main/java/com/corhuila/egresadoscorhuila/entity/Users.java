package com.corhuila.egresadoscorhuila.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigInteger;
import java.util.Date;

@Collation("users")
@Getter
@Setter
public class Users {

    @Id
    @NotNull
    private Long id;
    @Field(name = "primerNombre")
    @NotNull
    private String primerNombre;
    @Field(name = "segundoNombre")
    private String segundoNombre;
    @Field(name = "primerApellido")
    @NotNull
    private String primerApellido;
    @Field(name = "segundoApellido")
    @NotNull
    private String segundoApellido;
    @Field(name = "telefono")
    @NotNull
    private BigInteger telefono;
    @Field(name = "email")
    @NotNull
    private String email;
    @Field(name = "emailInstitucional")
    @NotNull
    private String emailInstitucional;
    @Field(name = "noIdentificacion")
    @NotNull
    private BigInteger noIdentificacion;
    @Field(name = "genero")
    @NotNull
    private String genero;
    @Field(name = "edad")
    @NotNull
    private String edad;
    @Field(name = "fechaNacimiento")
    @NotNull
    private Date fechaNacimiento;
    @Field(name = "carrera")
    @NotNull
    private String carrera;
    @Field(name = "anoGraduacion")
    @NotNull
    private String anoGraduacion;
    @Field(name = "fotoPerfil")
    @NotNull
    private Byte[] fotoPerfil;
    @Field(name = "password")
    @NotNull
    private String password;
    @Field(name = "rol")
    @NotNull
    private String rol;
}
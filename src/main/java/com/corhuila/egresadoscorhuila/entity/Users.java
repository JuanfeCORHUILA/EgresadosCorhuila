package com.corhuila.egresadoscorhuila.entity;

import com.corhuila.egresadoscorhuila.enums.RolEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Collation("users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {


//    @NotNull
//    private Long id;

    @Id
    @Field(name = "noIdentificacion")
    @NotNull
    private Long noIdentificacion;

    @Field(name = "emailInstitucional")
    @NotNull
    @Email
    private String emailInstitucional;

    @Field(name = "tipoDocumento")
    @NotNull
    private String tipoDocumento;

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
    @Email
    private String email;

    @Field(name = "genero")
    @NotNull
    private String genero;

    @Field(name = "edad")
    @NotNull
    private String edad;

    @Field(name = "fechaNacimiento")
    @NotNull
    @Past
    private Date fechaNacimiento;

    @Field(name = "programa")
    @NotNull
    private String programa;

    @Field(name = "fotoPerfil")
    @NotNull
    private Byte[] fotoPerfil;

    @Field(name = "ciudadRecidencia")
    @NotNull
    private String ciudadRecidencia;

    @Field(name = "direccionRecidencia")
    @NotNull
    private String direccionRecidencia;

    @Field(name = "sedeUniversitaria")
    @NotNull
    private String sedeUniversitaria;

    @Field(name = "ultimoNivelFormacion")
    @NotNull
    private String ultimoNivelFormacion;

    @Field(name = "facultad")
    @NotNull
    private String facultad;

    @Field(name = "ultimoSemestre")
    @NotNull
    private Long ultimoSemestre;

    @Field(name = "graduado")
    @NotNull
    private Boolean graduado;

    @Field(name = "fechaGrado")
    @NotNull
    private Date fechaGrado;

    @Field(name = "modalidad")
    @NotNull
    private String modalidad;

    @Field(name = "calificacionObtenida")
    @NotNull
    private String calificacionObtenida;

    @Field(name = "tituloTrabajoGrado")
    @NotNull
    private String tituloTrabajoGrado;

    @Field(name = "labora")
    @NotNull
    private Boolean labora;

    @Field(name = "nombreEmpresa")
    @NotNull
    private String nombreEmpresa;

    @Field(name = "rolEjecuta")
    @NotNull
    private String rolEjecuta;

    @Field(name = "fechaIngreso")
    @NotNull
    private Date fechaIngreso;

    @Field(name = "actividadEjecuta")
    @NotNull
    private String actividadEjecuta;

    @Field(name = "rangoSalarial")
    @NotNull
    private String rangoSalarial;

    @Field(name = "tipoContrato")
    @NotNull
    private String tipoContrato;

    @Field(name = "modalidadTrabajo")
    @NotNull
    private String modalidadTrabajo;

    @Field(name = "relacionFormacion")
    @NotNull
    private String relacionFormacion;

}
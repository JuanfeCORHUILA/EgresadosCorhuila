package com.corhuila.egresadoscorhuila.entity;

import com.corhuila.egresadoscorhuila.enums.RolEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users extends EntityId{

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
    private String genero;

    @Field(name = "edad")
    @NotNull
    private String edad;

    @Field(name = "fechaNacimiento")
    @NotNull
    @Past
    private Date fechaNacimiento;

    @Field(name = "programa")
    private String programa;

    @Field(name = "fotoPerfil")
    private Byte[] fotoPerfil;

    @Field(name = "ciudadRecidencia")
    @NotNull
    private String ciudadRecidencia;

    @Field(name = "direccionRecidencia")
    @NotNull
    private String direccionRecidencia;

    @Field(name = "sedeUniversitaria")
    private String sedeUniversitaria;

    @Field(name = "ultimoNivelFormacion")
    private String ultimoNivelFormacion;

    @Field(name = "facultad")
    private String facultad;

    @Field(name = "ultimoSemestre")
    private Long ultimoSemestre;

    @Field(name = "graduado")
    private Boolean graduado;

    @Field(name = "fechaGrado")
    private Date fechaGrado;

    @Field(name = "modalidad")
    private String modalidad;

    @Field(name = "calificacionObtenida")
    private String calificacionObtenida;

    @Field(name = "tituloTrabajoGrado")
    private String tituloTrabajoGrado;

    @Field(name = "tituloObtenido")
    private String tituloObtenido;

    @Field(name = "labora")
    private Boolean labora;

    @Field(name = "nombreEmpresa")
    private String nombreEmpresa;

    @Field(name = "rolEjecuta")
    private String rolEjecuta;

    @Field(name = "fechaIngreso")
    private Date fechaIngreso;

    @Field(name = "actividadEjecuta")
    private String actividadEjecuta;

    @Field(name = "rangoSalarial")
    private String rangoSalarial;

    @Field(name = "tipoContrato")
    private String tipoContrato;

    @Field(name = "modalidadTrabajo")
    private String modalidadTrabajo;

    @Field(name = "relacionFormacion")
    private String relacionFormacion;

}
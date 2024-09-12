package com.corhuila.egresadoscorhuila.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePassword {
    @NotNull
    private Long noIdentificacion;
    @NotNull
    private String email;
    @NotNull
    private String password;
}

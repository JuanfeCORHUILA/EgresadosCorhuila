package com.corhuila.egresadoscorhuila.dto;

import com.corhuila.egresadoscorhuila.enums.RolEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateUserDto {

    @NotNull
    private Long noIdentificacion;

    @NotNull
    @Email
    private String emailInstitucional;

    @NotNull
    private String password;

    @NotEmpty
    private List<String> rol;
}

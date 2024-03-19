package com.corhuila.egresadoscorhuila.entity;

import com.corhuila.egresadoscorhuila.enums.RolEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Collation("createUsers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUsers {

    @Id
    @Field(name = "noIdentificacion")
    @NotNull
    private Long noIdentificacion;

    @Field(name = "emailInstitucional")
    @NotNull
    @Email
    private String emailInstitucional;

    @Field(name = "password")
    @NotNull
    private String password;

    @Field(name = "rol")
    @NotNull
    private List<RolEnum> rol;
}

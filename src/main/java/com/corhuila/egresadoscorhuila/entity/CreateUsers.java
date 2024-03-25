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
import java.util.List;

@Document(collection = "createUsers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUsers extends EntityId{

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


    public CreateUsers(int id, Long noIdentificacion, String emailInstitucional, String password, List<RolEnum> roles) {
        this.id = id;
        this.noIdentificacion = noIdentificacion;
        this.emailInstitucional = emailInstitucional;
        this.password = password;
        this.rol = roles;
    }
}

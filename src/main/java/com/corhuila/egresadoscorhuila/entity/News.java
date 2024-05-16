package com.corhuila.egresadoscorhuila.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "news")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News extends EntityId{

    @Field(name = "title")
    @NotNull
    private String title;

    @Field(name = "content")
    @NotNull
    private String content;

    @Field(name = "file")
    @NotNull
    private Byte[] file;

    @Field(name = "status")
    @NotNull
    private Boolean status;

    @Field(name = "expirationDate")
    @NotNull
    private Date expirationDate;


}

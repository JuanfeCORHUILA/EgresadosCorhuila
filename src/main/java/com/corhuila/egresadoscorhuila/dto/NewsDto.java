package com.corhuila.egresadoscorhuila.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NewsDto {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Byte[] file;

    @NotNull
    private Boolean status;

    @NotNull
    private Date expirationDate;
}

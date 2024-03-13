package com.corhuila.egresadoscorhuila.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(prefix = {"res","res_"})
public class ResponseGeneric {

    @JsonProperty("message")
    private String resMessage;

    @JsonProperty("status")
    private String resStatus;

    @JsonProperty("codResponse")
    private Integer resCodResponse;

    @JsonProperty("object")
    private Object resObject;

    @JsonProperty("listObject")
    private List<Object> resListObject;
}

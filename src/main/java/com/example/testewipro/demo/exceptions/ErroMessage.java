package com.example.testewipro.demo.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErroMessage {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer statusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;

    public ErroMessage(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ErroMessage(String message, String field) {
        this.message = message;
        this.field = field;
    }
}

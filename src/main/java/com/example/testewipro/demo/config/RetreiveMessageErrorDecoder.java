package com.example.testewipro.demo.config;

import com.example.testewipro.demo.exceptions.ResourceNotFoundException;
import com.example.testewipro.demo.exceptions.ValidationExceptionCustom;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetreiveMessageErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        System.out.println("Error Response!!!");

        if (400 == response.status()) {
            return new ValidationExceptionCustom(response.reason());
        } else if (404 == response.status()) {
            return new ResourceNotFoundException(response.reason());
        }

        return errorDecoder.decode(s, response);
    }
}
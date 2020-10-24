package com.example.springbootcrediresponse.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class GenericResponse {

    private String message;

}
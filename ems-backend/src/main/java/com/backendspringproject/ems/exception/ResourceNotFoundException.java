package com.backendspringproject.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);

    }
}

// If employee with a given ID is not exist in database, then we will throw this
// ResourceNotFoundException and then springboot will catch this exception and it will get the error message from the exception
// and it will send the error message along with the Http status to the client

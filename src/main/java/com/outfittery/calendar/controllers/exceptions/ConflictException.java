package com.outfittery.calendar.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    public ConflictException() {
        super("You are trying to perform not allowed operation for entity");
    }

    public ConflictException(String message) {
        super(message);
    }
}

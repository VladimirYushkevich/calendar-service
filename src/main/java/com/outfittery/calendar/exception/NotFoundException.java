package com.outfittery.calendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -9093162198478106114L;

    public NotFoundException() {
        super("The entity you are looking for does not exist");
    }
}

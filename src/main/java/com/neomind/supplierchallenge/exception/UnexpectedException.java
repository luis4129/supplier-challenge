package com.neomind.supplierchallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnexpectedException extends RuntimeException {

	public UnexpectedException() {
        super("Um erro inexperado ocorreu, tente novamente mais tarde.");
    }

}

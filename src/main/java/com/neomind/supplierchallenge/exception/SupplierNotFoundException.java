package com.neomind.supplierchallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SupplierNotFoundException extends RuntimeException {
	
	public SupplierNotFoundException() {
        super("Não foi possível encontrar nenhum fornecedor com o id informado.");
    }

}

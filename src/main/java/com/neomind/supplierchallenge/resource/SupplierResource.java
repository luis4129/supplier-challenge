package com.neomind.supplierchallenge.resource;

import com.neomind.supplierchallenge.entity.Supplier;
import com.neomind.supplierchallenge.exception.SupplierNotFoundException;
import com.neomind.supplierchallenge.exception.UnexpectedException;
import com.neomind.supplierchallenge.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collection;

@RestController
@RequestMapping("/suppliers")
public class SupplierResource {
	
    @Autowired
    SupplierService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Supplier> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody Supplier supplier) {
        service.save(supplier);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier findById(@PathVariable("id") Long id) throws SupplierNotFoundException {
        return service.findById(id);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Supplier supplier, @PathVariable("id") Long id) throws SupplierNotFoundException {
        service.update(id, supplier);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws SupplierNotFoundException {
        service.delete(id);
    }

}

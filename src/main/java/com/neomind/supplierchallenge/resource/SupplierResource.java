package com.neomind.supplierchallenge.resource;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.neomind.supplierchallenge.entity.Supplier;
import com.neomind.supplierchallenge.exception.NotFoundException;
import com.neomind.supplierchallenge.service.SupplierService;

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
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier findById(@PathVariable("id") Long id) throws NotFoundException {
        return service.findById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody Supplier supplier) {
        service.save(supplier);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id, @RequestBody Supplier supplier) throws NotFoundException {
        service.update(id, supplier);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        service.delete(id);
    }

}

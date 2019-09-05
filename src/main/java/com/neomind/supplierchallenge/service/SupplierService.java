package com.neomind.supplierchallenge.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neomind.supplierchallenge.entity.Supplier;
import com.neomind.supplierchallenge.exception.NotFoundException;
import com.neomind.supplierchallenge.repository.SupplierRepository;

@Service
public class SupplierService {
	
	@Autowired
	SupplierRepository repository;
	
	public Collection<Supplier> findAll() {
		return repository.findAll();
	}
	
	public Supplier findById(Long id) throws NotFoundException {
		return repository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	public Supplier save(Supplier supplier) {
		return repository.save(supplier);
	}

	public void delete(Long id) throws NotFoundException {
		repository.delete(findById(id));		
	}
	
	public Supplier update(Long id, Supplier supplier) throws NotFoundException {
		findById(id);
		supplier.setId(id);
		return save(supplier);
	}	

}

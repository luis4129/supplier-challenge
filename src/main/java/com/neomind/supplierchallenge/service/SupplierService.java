package com.neomind.supplierchallenge.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neomind.supplierchallenge.entity.Supplier;
import com.neomind.supplierchallenge.exception.SupplierNotFoundException;
import com.neomind.supplierchallenge.repository.SupplierRepository;

@Service
public class SupplierService {
	
	@Autowired
	SupplierRepository repository;
	
	public Collection<Supplier> findAll() {
		return repository.findAll();
	}
	
	public Supplier findById(Long id) throws SupplierNotFoundException {
		return repository.findById(id).orElseThrow(SupplierNotFoundException::new);
	}
	
	public Supplier save(Supplier supplier) {
		return repository.save(supplier);
	}

	public Supplier update(Long id, Supplier supplier) throws SupplierNotFoundException {
		Supplier existingSupplier = findById(id);
		
		existingSupplier.setName(supplier.getName());
		existingSupplier.setComment(supplier.getComment());
		existingSupplier.setEmail(supplier.getEmail());
		existingSupplier.setCnpj(supplier.getCnpj());
		
		return save(existingSupplier);
	}

	public void delete(Long id) throws SupplierNotFoundException {
		repository.delete(findById(id));
	}

}

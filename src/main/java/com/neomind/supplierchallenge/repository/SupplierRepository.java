package com.neomind.supplierchallenge.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.neomind.supplierchallenge.entity.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
	
	public Collection<Supplier> findAll();

}

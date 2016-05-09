package com.rest.example.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.rest.example.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, BigInteger> {

}

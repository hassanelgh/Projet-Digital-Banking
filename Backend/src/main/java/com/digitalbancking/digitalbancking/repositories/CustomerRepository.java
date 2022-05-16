package com.digitalbancking.digitalbancking.repositories;

import com.digitalbancking.digitalbancking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}

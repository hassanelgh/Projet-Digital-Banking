package com.digitalbancking.digitalbancking.repositories;

import com.digitalbancking.digitalbancking.entities.BankAccount;
import com.digitalbancking.digitalbancking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}

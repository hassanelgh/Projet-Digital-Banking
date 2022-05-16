package com.digitalbancking.digitalbancking.repositories;

import com.digitalbancking.digitalbancking.entities.AccountOperation;
import com.digitalbancking.digitalbancking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}

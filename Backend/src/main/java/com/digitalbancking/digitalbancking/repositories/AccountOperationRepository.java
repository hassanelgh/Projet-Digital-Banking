package com.digitalbancking.digitalbancking.repositories;

import com.digitalbancking.digitalbancking.entities.AccountOperation;
import com.digitalbancking.digitalbancking.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    List<AccountOperation> findByBankAccountId(String id);
    Page<AccountOperation> findByBankAccountId(String id,Pageable pageable);
}

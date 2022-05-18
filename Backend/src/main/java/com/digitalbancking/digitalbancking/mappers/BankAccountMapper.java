package com.digitalbancking.digitalbancking.mappers;

import com.digitalbancking.digitalbancking.dtos.*;
import com.digitalbancking.digitalbancking.entities.*;

import java.util.List;

public interface BankAccountMapper {

    CustomerDTO fromCustomer(Customer customer);
    Customer fromCustomerDTO(CustomerDTO customerDTO);

    Customer fromRequestCustomerDTO(RequestCustomerDTO requestCustomerDTO);

    CurrentAccountDTO fromCurrentAccount(CurrentAccount currentAccount);
    CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentAccountDTO);

    SavingAccountDTO fromSavingAccount(SavingAccount savingAccount);
    SavingAccount fromSavingAccountDTO(SavingAccountDTO savingAccountDTO);

    AccountOperationDTO fromAccountOperation(AccountOperation accountOperation);

    AccountOperation fromAccountOperationDTO(AccountOperationDTO accountOperationDTO);

    CurrentAccount fromRequestCurrentAccountDTOAndCustomer(RequestSaveCurrentAccountDTO requestSaveCurrentAccountDTO, Customer customer);

    SavingAccount fromRequestSavingAccountDTOAndCustomer(RequestSaveSavingAccountDTO requestSaveSavingAccountDTO, Customer customer);

    AccountOperation fromDebitDTOAndBankAccount(RequestDebitDTO debitDTO, BankAccount bankAccount);

    AccountOperation fromCreditDTOAndBankAccount(RequestCreditDTO creditDTO, BankAccount bankAccount);

    AccountHistoryDTO toAccountHistoryDTO(RequestAccountHistory requestAccountHistory, BankAccount bankAccount,
                                          List<AccountOperationDTO> accountOperationDTOS, int totalPages, CustomerDTO customerDTO);
}

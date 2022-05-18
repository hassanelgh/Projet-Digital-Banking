package com.digitalbancking.digitalbancking.services;

import com.digitalbancking.digitalbancking.dtos.*;
import com.digitalbancking.digitalbancking.exceptions.BalanceNotSufficientException;
import com.digitalbancking.digitalbancking.exceptions.BankAccountNotFoundException;
import com.digitalbancking.digitalbancking.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(RequestCustomerDTO requestCustomerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(String customerId);

    CurrentAccountDTO saveCurrentBankAccount(RequestSaveCurrentAccountDTO requestSaveCurrentAccountDTO) throws CustomerNotFoundException;
    SavingAccountDTO saveSavingBankAccount(RequestSaveSavingAccountDTO requestSaveSavingAccountDTO) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    List<BankAccountDTO> listBankAccount();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    CustomerDTO getCustomer(String customerId) throws CustomerNotFoundException;

    void  debit(RequestDebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void  credit(RequestCreditDTO creditDTO) throws BankAccountNotFoundException;
    void  transfer(RequestTransferDTO requestTransferDTOt) throws BankAccountNotFoundException, BalanceNotSufficientException;


    List<AccountOperationDTO> accountOperations(String accountId);

    AccountHistoryDTO accountHistory(RequestAccountHistory requestAccountHistory) throws BankAccountNotFoundException;
}

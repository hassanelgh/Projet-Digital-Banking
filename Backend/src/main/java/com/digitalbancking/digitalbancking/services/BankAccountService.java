package com.digitalbancking.digitalbancking.services;

import com.digitalbancking.digitalbancking.entities.BankAccount;
import com.digitalbancking.digitalbancking.entities.CurrentAccount;
import com.digitalbancking.digitalbancking.entities.Customer;
import com.digitalbancking.digitalbancking.entities.SavingAccount;
import com.digitalbancking.digitalbancking.exceptions.BalanceNotSufficientException;
import com.digitalbancking.digitalbancking.exceptions.BankAccountNotFoundException;
import com.digitalbancking.digitalbancking.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, String customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, String customerId) throws CustomerNotFoundException;
    List<Customer> listCustomers();
    List<BankAccount> listBankAccount();
    BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;
    void  debit(String accountId,double amount,String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void  credit(String accountId,double amount,String description) throws BankAccountNotFoundException;
    void  transfer(String accountIdSource, String accountIdDestination,double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;



}

package com.digitalbancking.digitalbancking.mappers;

import com.digitalbancking.digitalbancking.dtos.*;
import com.digitalbancking.digitalbancking.entities.*;
import com.digitalbancking.digitalbancking.enums.OperationType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BankAccountMapperImpl implements BankAccountMapper {
    @Override
    public CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO=new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        return customerDTO;
    }

    @Override
    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
       Customer customer=new Customer();
       BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }

    @Override
    public Customer fromRequestCustomerDTO(RequestCustomerDTO requestCustomerDTO) {
        Customer customer=new Customer();
        BeanUtils.copyProperties(requestCustomerDTO,customer);
        return customer;
    }

    @Override
    public CurrentAccountDTO fromCurrentAccount(CurrentAccount currentAccount) {
        CurrentAccountDTO currentAccountDTO=new CurrentAccountDTO();
        BeanUtils.copyProperties(currentAccount,currentAccountDTO);
        currentAccountDTO.setType("Current");
        currentAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        return currentAccountDTO;
    }
    @Override
    public CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentAccountDTO) {
        CurrentAccount currentAccount=new CurrentAccount();
        BeanUtils.copyProperties(currentAccountDTO,currentAccount);
        currentAccount.setCustomer(fromCustomerDTO(currentAccountDTO.getCustomerDTO()));
        return currentAccount;
    }


    @Override
    public SavingAccountDTO fromSavingAccount(SavingAccount savingAccount) {
        SavingAccountDTO savingAccountDTO=new SavingAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingAccountDTO);
        savingAccountDTO.setType("Saving");
        savingAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));

        return savingAccountDTO;
    }

    @Override
    public SavingAccount fromSavingAccountDTO(SavingAccountDTO savingAccountDTO) {
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO,savingAccount);
        savingAccount.setCustomer(fromCustomerDTO(savingAccountDTO.getCustomerDTO()));
        return savingAccount;
    }



    @Override
    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO=new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationDTO);
        return accountOperationDTO;
    }


    @Override
    public AccountOperation fromAccountOperationDTO(AccountOperationDTO accountOperationDTO){
        AccountOperation accountOperation=new AccountOperation();
        BeanUtils.copyProperties(accountOperationDTO,accountOperation);
        return accountOperation;
    }

    @Override
    public CurrentAccount fromRequestCurrentAccountDTOAndCustomer(RequestSaveCurrentAccountDTO requestSaveCurrentAccountDTO, Customer customer) {
        CurrentAccount bankAccount=new CurrentAccount();

        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        bankAccount.setBalance(requestSaveCurrentAccountDTO.getBalance());
        bankAccount.setCustomer(customer);
        bankAccount.setOverDraft(requestSaveCurrentAccountDTO.getOverDraft());
        return bankAccount;
    }

    @Override
    public SavingAccount fromRequestSavingAccountDTOAndCustomer(RequestSaveSavingAccountDTO requestSaveSavingAccountDTO, Customer customer) {
        SavingAccount bankAccount=new SavingAccount();

        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        bankAccount.setBalance(requestSaveSavingAccountDTO.getBalance());
        bankAccount.setCustomer(customer);
        bankAccount.setInterestRate(requestSaveSavingAccountDTO.getInterestRate());
        return bankAccount;
    }

    @Override
    public AccountOperation fromDebitDTOAndBankAccount(RequestDebitDTO debitDTO, BankAccount bankAccount) {
        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(debitDTO.getAmount());
        accountOperation.setOperationDate(new Date());
        accountOperation.setDescription(debitDTO.getDescription());
        accountOperation.setBankAccount(bankAccount);
        return accountOperation;
    }

    @Override
    public AccountOperation fromCreditDTOAndBankAccount(RequestCreditDTO creditDTO, BankAccount bankAccount) {

        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(creditDTO.getAmount());
        accountOperation.setOperationDate(new Date());
        accountOperation.setDescription(creditDTO.getDescription());
        accountOperation.setBankAccount(bankAccount);

        return accountOperation;
    }

    @Override
    public AccountHistoryDTO toAccountHistoryDTO(RequestAccountHistory requestAccountHistory, BankAccount bankAccount,
                                                 List<AccountOperationDTO> accountOperationDTOS, int totalPages, CustomerDTO customerDTO) {
        AccountHistoryDTO accountHistoryDTO=new AccountHistoryDTO();
        accountHistoryDTO.setAccountID(requestAccountHistory.getAccountId());
        accountHistoryDTO.setAccountOperationDTOS(accountOperationDTOS);
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setCurrentPage(requestAccountHistory.getPage());
        accountHistoryDTO.setPageSize(requestAccountHistory.getSize());
        accountHistoryDTO.setTotalPages(totalPages);
        accountHistoryDTO.setCustomerDTO(customerDTO);
        return accountHistoryDTO;
    }
}


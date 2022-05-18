package com.digitalbancking.digitalbancking.services;

import com.digitalbancking.digitalbancking.dtos.*;
import com.digitalbancking.digitalbancking.entities.*;
import com.digitalbancking.digitalbancking.exceptions.BalanceNotSufficientException;
import com.digitalbancking.digitalbancking.exceptions.BankAccountNotFoundException;
import com.digitalbancking.digitalbancking.exceptions.CustomerNotFoundException;
import com.digitalbancking.digitalbancking.mappers.BankAccountMapper;
import com.digitalbancking.digitalbancking.repositories.AccountOperationRepository;
import com.digitalbancking.digitalbancking.repositories.BankAccountRepository;
import com.digitalbancking.digitalbancking.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {
    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapper bankAccountMapper;

    @Override
    public CustomerDTO saveCustomer(RequestCustomerDTO requestCustomerDTO) {
        Customer customer=bankAccountMapper.fromRequestCustomerDTO(requestCustomerDTO);
        customer.setId(UUID.randomUUID().toString());
        Customer saveCystomer = customerRepository.save(customer);

        log.info("saving new Customer");
        return  bankAccountMapper.fromCustomer(saveCystomer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer=bankAccountMapper.fromCustomerDTO(customerDTO);
        Customer saveCystomer = customerRepository.save(customer);
        log.info("update new Customer");
        return  bankAccountMapper.fromCustomer(saveCystomer);
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
        log.info("delete customer "+customerId);
    }

    @Override
    public CurrentAccountDTO saveCurrentBankAccount(RequestSaveCurrentAccountDTO requestSaveCurrentAccountDTO) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(requestSaveCurrentAccountDTO.getCustomerID()).orElse(null);
        if(customer==null)
            throw new CustomerNotFoundException("customer not fount");
        CurrentAccount bankAccount=bankAccountMapper.fromRequestCurrentAccountDTOAndCustomer(requestSaveCurrentAccountDTO,customer);
        CurrentAccount saveBankAccount= bankAccountRepository.save(bankAccount);
        log.info("saving new Current Bank Account");
        return bankAccountMapper.fromCurrentAccount(saveBankAccount);
    }

    @Override
    public SavingAccountDTO saveSavingBankAccount(RequestSaveSavingAccountDTO requestSaveSavingAccountDTO) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(requestSaveSavingAccountDTO.getCustomerID()).orElse(null);
        if(customer==null)
            throw new CustomerNotFoundException("customer not fount");
        SavingAccount bankAccount=bankAccountMapper.fromRequestSavingAccountDTOAndCustomer(requestSaveSavingAccountDTO,customer);
        SavingAccount saveBankAccount= bankAccountRepository.save(bankAccount);
        log.info("saving new Saving Bank Account");
        return bankAccountMapper.fromSavingAccount(saveBankAccount);
    }


    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customers=customerRepository.findAll();
        List<CustomerDTO> customersDTO = customers.stream().map(customer -> bankAccountMapper.fromCustomer(customer)).collect(Collectors.toList());
        log.info("get all Customers");
        return customersDTO;
    }

    @Override
    public List<BankAccountDTO> listBankAccount() {

        List<BankAccount> bankAccounts=bankAccountRepository.findAll();
        List<BankAccountDTO> bankAccountDTOS= bankAccounts.stream().map(bankAccount ->{
            if(bankAccount instanceof CurrentAccount){
                return bankAccountMapper.fromCurrentAccount((CurrentAccount) bankAccount);
            }else{
                return bankAccountMapper.fromSavingAccount((SavingAccount) bankAccount);
            }
        }).collect(Collectors.toList());
        log.info("get all Bank Account");
        return bankAccountDTOS;
    }

    @Override
    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = findBankAccount(accountId);
        log.info("get the Bank Account "+accountId);
        if(bankAccount instanceof CurrentAccount){
            return bankAccountMapper.fromCurrentAccount((CurrentAccount) bankAccount);
        }else{
            return bankAccountMapper.fromSavingAccount((SavingAccount) bankAccount);
        }
    }

    @Override
    public CustomerDTO getCustomer(String customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("customer not found"));
        log.info("get the customer "+customerId);
        return bankAccountMapper.fromCustomer(customer);
    }


    private  BankAccount findBankAccount(String accountId) throws BankAccountNotFoundException {
        return bankAccountRepository.findById(accountId).orElseThrow(() -> new BankAccountNotFoundException("bankAccount not found"));
    }


    @Override
    public void debit(RequestDebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount =findBankAccount(debitDTO.getAccountId());
        if(bankAccount.getBalance()<debitDTO.getAmount())
            throw  new BalanceNotSufficientException("balance not sufficient");

        AccountOperation accountOperation=bankAccountMapper.fromDebitDTOAndBankAccount(debitDTO,bankAccount);

        accountOperationRepository.save(accountOperation);

        bankAccount.setBalance(bankAccount.getBalance()-debitDTO.getAmount());
        bankAccountRepository.save(bankAccount);

        log.info("Account "+ debitDTO.getAccountId()+ " ==> Debit :"+debitDTO.getAmount());

    }

    @Override
    public void credit(RequestCreditDTO creditDTO) throws BankAccountNotFoundException {
        BankAccount bankAccount = findBankAccount(creditDTO.getAccountId());
        AccountOperation accountOperation=bankAccountMapper.fromCreditDTOAndBankAccount(creditDTO,bankAccount);
        accountOperationRepository.save(accountOperation);

        bankAccount.setBalance(bankAccount.getBalance()+creditDTO.getAmount());
        bankAccountRepository.save(bankAccount);

        log.info("Account "+ creditDTO.getAccountId()+ " ==> Credit :"+creditDTO.getAmount());

    }

    @Override
    public void transfer(RequestTransferDTO requestTransferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(new RequestDebitDTO(
                requestTransferDTO.getAccountSource(),
                requestTransferDTO.getAmount(),
                "Transfer to "+requestTransferDTO.getAccountDestination())
        );
        credit(new RequestCreditDTO(
                requestTransferDTO.getAccountDestination(),
                requestTransferDTO.getAmount(),
                "Transfer from "+requestTransferDTO.getAccountSource())
        );

        log.info(requestTransferDTO.getAmount()+" transferred from "+ requestTransferDTO.getAccountSource() +" to "+
                requestTransferDTO.getAccountDestination());

    }


    @Override
    public List<AccountOperationDTO> accountOperations(String accountId){
        List<AccountOperation> accountOperations=accountOperationRepository.findByBankAccountId(accountId);
        List<AccountOperationDTO> accountOperationDTOS = accountOperations.stream().map(accountOperation ->
                bankAccountMapper.fromAccountOperation(accountOperation)).collect(Collectors.toList());
        log.info("get operations of "+accountId);
        return accountOperationDTOS;
    }

    @Override
    public AccountHistoryDTO accountHistory(RequestAccountHistory requestAccountHistory) throws BankAccountNotFoundException {
        BankAccount bankAccount=findBankAccount(requestAccountHistory.getAccountId());
        Page<AccountOperation> pageAccountOperations=accountOperationRepository.findByBankAccountId(
                requestAccountHistory.getAccountId(),
                PageRequest.of(requestAccountHistory.getPage(), requestAccountHistory.getSize()));
        List<AccountOperationDTO> accountOperationDTOS = pageAccountOperations.getContent().stream().map(accountOperation ->
                bankAccountMapper.fromAccountOperation(accountOperation)).collect(Collectors.toList());

        AccountHistoryDTO accountHistoryDTO=bankAccountMapper.toAccountHistoryDTO(
                requestAccountHistory,bankAccount,accountOperationDTOS,pageAccountOperations.getTotalPages()
                ,bankAccountMapper.fromCustomer(bankAccount.getCustomer()));

        log.info("get history of "+requestAccountHistory.getAccountId());
        return accountHistoryDTO;
    }



}

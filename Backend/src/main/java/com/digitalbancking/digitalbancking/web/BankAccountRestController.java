package com.digitalbancking.digitalbancking.web;

import com.digitalbancking.digitalbancking.dtos.*;
import com.digitalbancking.digitalbancking.exceptions.BalanceNotSufficientException;
import com.digitalbancking.digitalbancking.exceptions.BankAccountNotFoundException;
import com.digitalbancking.digitalbancking.exceptions.CustomerNotFoundException;
import com.digitalbancking.digitalbancking.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestController {

    BankAccountService bankAccountService;

    @GetMapping("/accounts/{id}")
    public BankAccountDTO getBankAccount(@PathVariable(name = "id") String id) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(id);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> bankAccounts(){
        return bankAccountService.listBankAccount();
    }



    @GetMapping("/accounts/{id}/operations")
    public List<AccountOperationDTO> getOperations(@PathVariable(name = "id") String id){
        return  bankAccountService.accountOperations(id);
    }


    @GetMapping("/accounts/{id}/history")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable(name = "id") String id,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size
    ) throws BankAccountNotFoundException {
        return  bankAccountService.accountHistory(new RequestAccountHistory(id,page,size));
    }


    @PostMapping("/accounts/saving")
    public SavingAccountDTO saveSavingBankAccount(@RequestBody RequestSaveSavingAccountDTO requestSaveSavingAccountDTO)
            throws CustomerNotFoundException {
        return bankAccountService.saveSavingBankAccount(requestSaveSavingAccountDTO);
    }

    @PostMapping("/accounts/current")
    public CurrentAccountDTO saveCurrentBankAccount(@RequestBody RequestSaveCurrentAccountDTO requestSaveCurrentAccountDTO)
            throws CustomerNotFoundException {
        return bankAccountService.saveCurrentBankAccount(requestSaveCurrentAccountDTO);
    }

    @PostMapping("/accounts/debit")
    public RequestDebitDTO debit(@RequestBody RequestDebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.debit(debitDTO);
        return debitDTO;
    }

    @PostMapping("/accounts/credit")
    public RequestCreditDTO credit(@RequestBody RequestCreditDTO creditDTO) throws BankAccountNotFoundException {
        this.bankAccountService.credit(creditDTO);
        return creditDTO;
    }
    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody RequestTransferDTO requestTransferDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountService.transfer(requestTransferDTO);
    }

}

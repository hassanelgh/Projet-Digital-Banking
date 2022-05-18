package com.digitalbancking.digitalbancking.web;

import com.digitalbancking.digitalbancking.dtos.CustomerDTO;
import com.digitalbancking.digitalbancking.dtos.RequestCustomerDTO;
import com.digitalbancking.digitalbancking.exceptions.CustomerNotFoundException;
import com.digitalbancking.digitalbancking.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return  bankAccountService.listCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") String customerId) throws CustomerNotFoundException {
        return  bankAccountService.getCustomer(customerId);
    }


    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody RequestCustomerDTO requestCustomerDTO){
        return bankAccountService.saveCustomer(requestCustomerDTO);
    }


    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable(name = "id") String customerId,@RequestBody RequestCustomerDTO requestCustomerDTO){
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setId(customerId);
        customerDTO.setEmail(requestCustomerDTO.getEmail());
        customerDTO.setName(requestCustomerDTO.getName());
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable(name = "id") String customerId){
        bankAccountService.deleteCustomer(customerId);
    }


}

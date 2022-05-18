package com.digitalbancking.digitalbancking.dtos;

import com.digitalbancking.digitalbancking.entities.AccountOperation;
import com.digitalbancking.digitalbancking.entities.Customer;
import com.digitalbancking.digitalbancking.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data @NoArgsConstructor @AllArgsConstructor
public class BankAccountDTO {

    private String id;
    private String type;
    private double balance;
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private CustomerDTO customerDTO;


}

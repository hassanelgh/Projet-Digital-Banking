package com.digitalbancking.digitalbancking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RequestSaveSavingAccountDTO {
    private double balance;
    private double interestRate;
    private String customerID;


}

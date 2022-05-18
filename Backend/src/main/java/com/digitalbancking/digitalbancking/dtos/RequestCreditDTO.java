package com.digitalbancking.digitalbancking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RequestCreditDTO {
    private String accountId;
    private double amount;
    private String description;
}

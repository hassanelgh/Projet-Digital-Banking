package com.digitalbancking.digitalbancking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RequestSaveCurrentAccountDTO {
    private double balance;
    private double overDraft;
    private String customerID;
}

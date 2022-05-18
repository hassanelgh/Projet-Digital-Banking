package com.digitalbancking.digitalbancking.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CurrentAccountDTO extends BankAccountDTO {
    private  double overDraft;
}

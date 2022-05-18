package com.digitalbancking.digitalbancking.dtos;

import com.digitalbancking.digitalbancking.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountOperationDTO {

    private Long id;
    private Date operationDate;
    private  double amount;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    private String Description;
}

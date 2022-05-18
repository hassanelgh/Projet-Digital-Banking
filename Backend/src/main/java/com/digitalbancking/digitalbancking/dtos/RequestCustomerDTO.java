package com.digitalbancking.digitalbancking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RequestCustomerDTO {
    private String name;
    private  String email;
}

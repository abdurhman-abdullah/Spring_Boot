package com.example.springbootproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Domains {
    private String productId;
    private Category categoryId;
    private String merchantId;
    private MerchantStock stockId;
    private User userId;
}

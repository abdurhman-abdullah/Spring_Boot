package com.example.springbootproject.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
@AllArgsConstructor
@Data
public class MerchantStock {

    @NotEmpty(message = "id must not be empty")
    @Length(min = 3, message = "id have to be 3 character long")
    private String id;

    @NotEmpty(message = "productId must not be null")
    @Length(min = 3, message = "productId have to be 3 character long")
    private String productId;

    @NotEmpty(message = "merchantId must not be null")
    @Length(min = 3, message = "merchantId have to be 3 character long")
    private String merchantId;

    @NotEmpty(message = "stock must not be empty")
    @Length(min = 10, message = "stock have to be more than 10 at start")
    private String stock;
}

package com.ihor.productspec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specification {

    private Product sourceProduct;

    private Product targetProduct;

    private long quantity;
}

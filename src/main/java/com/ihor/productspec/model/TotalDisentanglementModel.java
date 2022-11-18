package com.ihor.productspec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalDisentanglementModel {
    private String productId;
    private String componentId;
    private long totalQuantity;
    private int maxNodeLevel;
    private ProductType productType;
}

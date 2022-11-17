package com.ihor.productspec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsabilityModel {
    private String componentId;
    private String assemblyId;
    private long quantity;
    private ProductType productType;
}

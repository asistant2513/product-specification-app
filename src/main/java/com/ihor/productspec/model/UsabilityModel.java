package com.ihor.productspec.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsabilityModel {
    private String currentComponent;
    private String componentId;
    private String assemblyId;
    private long quantity;
    private int nodeLevel;
    private String treeNodeLevel;
}

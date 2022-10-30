package com.ihor.productspec.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisentanglementModel {

    private String productId;

    private String assemblyId;

    private String componentId;

    private long quantity;

    private int nodeLevel;

    private String treeNodeLevel;
}

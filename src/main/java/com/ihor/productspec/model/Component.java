package com.ihor.productspec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Component {

    private String componentId;

    private String componentName;

    private long quantity;
}

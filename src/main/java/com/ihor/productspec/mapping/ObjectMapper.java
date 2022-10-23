package com.ihor.productspec.mapping;

import java.sql.ResultSet;
import java.util.List;

public interface ObjectMapper <T> {

    T mapOne(final ResultSet resultSet);

    List<T> mapAll(final ResultSet resultSet);
}

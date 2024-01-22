package com.yk.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer implements Serializable {
    private static final long serialVersionUID = -9003011625770547767L;
    @QuerySqlField(index = true)
    private Long id;
    @QuerySqlField
    private String name;
    @QuerySqlField
    private Long income;

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", income='" + income + '\'' +
                '}';
    }
}

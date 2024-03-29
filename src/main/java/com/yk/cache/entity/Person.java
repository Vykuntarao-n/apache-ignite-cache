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
public class Person  implements Serializable {


    private static final long serialVersionUID = -2207330477169300981L;
    @QuerySqlField(index = true)
    private Long id;
    @QuerySqlField
    private String firstName;
    @QuerySqlField
    private String lastName;
    @QuerySqlField
    private int age;
    /** Organization ID (indexed). */
    @QuerySqlField(index = true)
    public Long orgId;

}

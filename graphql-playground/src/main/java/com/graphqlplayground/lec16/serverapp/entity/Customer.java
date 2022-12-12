package com.graphqlplayground.lec16.serverapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Customer {

    @Id
    private Integer id;
    private String name;
    private Integer age;
    private String city;

}

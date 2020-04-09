package com.Car_Rental_Spring.controller.requests;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {

    private String query;

    private Integer limit;

    private Integer offset;
}

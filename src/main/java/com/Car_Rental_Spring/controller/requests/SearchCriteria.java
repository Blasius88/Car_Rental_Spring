package com.Car_Rental_Spring.controller.requests;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class SearchCriteria {
    private String query;
    private Integer limit;
    private Integer offset;
}

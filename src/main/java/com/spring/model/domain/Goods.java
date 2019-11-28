package com.spring.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goods {

    private Long id;

    @Min(0)
    @Max(10000)
    private int code;

    private String name;

    @Min(1)
    @Max(100000)
    private double quant;

    @Min(1)
    @Max(100000)
    private double price;

    private String measure;

    private String comments;

    private Collection<Checkspec> checkspecCollection;
}

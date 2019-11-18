package springBoot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import springBoot.domain.enums.ProductType;

import javax.validation.constraints.*;


@Data
@Builder
@AllArgsConstructor

public class Product {
    private Long id;

    @Pattern(regexp = "(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}")
    @NotEmpty
    private String name;

    @Min(1)
    @Max(1000000)
    @NotNull
    private Double price;

    @NotNull
    private ProductType productType;

    @Min(1)
    @Max(1000)
    @NotNull
    private Long amount;
}

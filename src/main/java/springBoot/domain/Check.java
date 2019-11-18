package springBoot.domain;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class Check {
    private Long id;

    @NotNull
    private User user;

    @NotNull
    private LocalDateTime time;

    @Min(1)
    @Max(10000000)
    @NotNull
    private BigDecimal total;

    @NotNull
    private boolean toDelete;

    private Map<Product, Long> productAmount;
}

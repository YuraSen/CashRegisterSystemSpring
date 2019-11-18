package springBoot.domain;


import lombok.*;
import springBoot.domain.enums.ReportType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor

public class Report {
    private Long id;

    @NotNull
    private LocalDate date;

    @Min(1)
    @Max(500000000)
    @NotNull
    private BigDecimal total;

    @NotNull
    private User user;

    @NotNull
    private Set<Check> checkEntities;

    @NotNull
    private ReportType reportType;
}

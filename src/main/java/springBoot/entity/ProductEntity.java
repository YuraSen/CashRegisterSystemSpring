package springBoot.entity;

import lombok.*;
import springBoot.domain.enums.ProductType;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType productType;
}

package springBoot.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "checkEntities")
public class CheckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, length = 5)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity userEntity;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "to_delete", nullable = false)
    private boolean toDelete;

    @ElementCollection
    @CollectionTable(name = "check_products_mapping",
            joinColumns = {@JoinColumn(name = "check_id", referencedColumnName = "id")})
    @Column(name = "amount")
    private Map<ProductEntity, Long> productAmount;
}

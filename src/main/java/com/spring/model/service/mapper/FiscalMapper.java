package com.spring.model.service.mapper;

import com.spring.model.domain.Fiscal;
import com.spring.model.entity.FiscalEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor

public class FiscalMapper {
    public Fiscal fiscalEntityToFiscal(FiscalEntity fiscalEntity) {
        if (fiscalEntity == null) {
            return null;
        }

        return Fiscal.builder()
                .id(fiscalEntity.getId())
                .total(fiscalEntity.getTotal())
                .build();
    }

    public FiscalEntity fiscalToFiscalEntity(Fiscal fiscal) {
        if (fiscal == null) {
            return null;
        }
        return FiscalEntity.builder()
                .id(fiscal.getId())
                .total(fiscal.getTotal())
                .build();
    }
}

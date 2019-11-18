package springBoot.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import springBoot.domain.Product;
import springBoot.entity.ProductEntity;

@Component
@Mapper
public interface ProductMapper {
    Product productEntityToProduct(ProductEntity checkEntity);

    ProductEntity productToProductEntity(Product check);
}


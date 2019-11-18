package springBoot.service.mapper;

import org.mapstruct.Mapper;
import springBoot.domain.Product;
import springBoot.entity.ProductEntity;

@Mapper
public interface ProductMapper {
    Product productToProductEntity(ProductEntity checkEntity);

    ProductEntity productEntityToProduct(Product check);
}


package springBoot.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import springBoot.domain.Product;
import springBoot.entity.ProductEntity;
import springBoot.exception.EntityNotFoundRuntimeException;
import springBoot.repository.ProductRepository;
import springBoot.service.ProductService;
import springBoot.service.mapper.ProductMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository reportRepository, ProductMapper mapper) {
        this.productRepository = reportRepository;
        this.mapper = mapper;
    }

    @Override
    public Product findByName(String name) {
        Optional<ProductEntity> result = productRepository.findByName(name);

        ProductEntity productEntity = result.
                orElseThrow(() -> {
                    log.warn("Product don't find by name");
                    throw new EntityNotFoundRuntimeException("Product don't find by name");
                });
        return mapper.productEntityToProduct(productEntity);
    }

    @Override
    public List<Product> findAll(Integer currentPage, Integer recordsPerPage) {
        PageRequest pageRequest = PageRequest.of(currentPage, recordsPerPage);
        Page<ProductEntity> result = productRepository.findAll(pageRequest);

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::productEntityToProduct)
                .collect(Collectors.toList());
    }

    @Override
    public long showNumberOfRows() {
        return productRepository.count();
    }
}

package springBoot.service;

import springBoot.domain.Product;

import java.util.List;

public interface ProductService {
    Product findByName(String name);

    List<Product> findAll(Integer currentPage, Integer recordsPerPage);

    long showNumberOfRows();
}

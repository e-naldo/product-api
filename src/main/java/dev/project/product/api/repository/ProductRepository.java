package dev.project.product.api.repository;

import dev.project.product.api.domain.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "productGroup")
    List<Product> findAllByNameContainingIgnoreCase(String name);

    @EntityGraph(attributePaths = "productGroup")
    List<Product> findAll();
}
package dev.project.product.api.repository;

import dev.project.product.api.domain.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {
    List<ProductGroup> findAllByNameContainingIgnoreCase(String name);
}

package dev.project.product.api.repository;

import dev.project.product.api.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "productGroup")
    Page<Product> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    @EntityGraph(attributePaths = "productGroup")
    Page<Product> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "productGroup")
    @Query("select p from Product p where " +
            "(:id is null or p.id = :id) and" +
            "(:name is null or lower(p.name) like lower(concat('%', :name, '%'))) and" +
            "(:productGroupId is null or p.productGroup.id = :productGroupId) and" +
            "(:reference is null or p.reference = :reference)")
    Page<Product> findAllByFilter(Long id, Long productGroupId,
                                  String reference, String name, Pageable pageable);
}
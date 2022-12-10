package dev.project.product.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_group_id")
    private ProductGroup productGroup;

    private String reference;

    private String name;

    private String unity;

    @Column(name = "cost_price")
    private BigDecimal costPrice = BigDecimal.valueOf(0.00);

    @Column(name = "sale_price")
    private BigDecimal salePrice ;

    public Product(ProductGroup productGroup, String reference, String name, String unity, BigDecimal costPrice) {
        this.productGroup = productGroup;
        this.reference = reference;
        this.name = name;
        this.unity = unity;
        this.costPrice = costPrice;
        calculatePrice();
    }

    public void calculatePrice() {
        salePrice = costPrice.multiply(new BigDecimal("2.05"));
    }
}

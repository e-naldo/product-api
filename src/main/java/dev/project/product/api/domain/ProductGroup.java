package dev.project.product.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_group")
public class ProductGroup extends BaseEntity {

    private String name;

    private Boolean active;

    @OneToMany(mappedBy = "productGroup", orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public void inactivate(){
        this.active = false;
        // inactive products in cascade
        products.forEach(p -> p.inactivate());
    }
}

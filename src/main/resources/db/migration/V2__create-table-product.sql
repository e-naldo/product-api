CREATE TABLE product (
  id BIGINT AUTO_INCREMENT NOT NULL,
   product_group_id BIGINT NOT NULL,
   reference VARCHAR(20) NOT NULL,
   name VARCHAR(150) NOT NULL,
   unity VARCHAR(4) NOT NULL,
   cost_price DECIMAL(12,2) NOT NULL,
   sale_price DECIMAL(12,2) NOT NULL,
   CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product ADD CONSTRAINT fk_product_product_group FOREIGN KEY (product_group_id) REFERENCES product_group (id);
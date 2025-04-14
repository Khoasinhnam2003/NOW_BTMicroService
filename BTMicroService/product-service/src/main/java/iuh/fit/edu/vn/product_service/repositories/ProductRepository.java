package iuh.fit.edu.vn.product_service.repositories;

import iuh.fit.edu.vn.product_service.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

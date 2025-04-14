package iuh.fit.edu.vn.order_service.repositories;

import iuh.fit.edu.vn.order_service.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

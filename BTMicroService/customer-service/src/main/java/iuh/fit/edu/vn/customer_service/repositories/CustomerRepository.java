package iuh.fit.edu.vn.customer_service.repositories;

import iuh.fit.edu.vn.customer_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}

package iuh.fit.edu.vn.customer_service.services;

import iuh.fit.edu.vn.customer_service.entities.Customer;
import iuh.fit.edu.vn.customer_service.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Customer getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public Customer create(Customer customer) {
        // Kiểm tra dữ liệu đầu vào
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        if (customer.getPhone() == null || customer.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer phone cannot be empty");
        }

        // Lưu customer vào database
        try {
            return repository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create customer: " + e.getMessage());
        }
    }
}

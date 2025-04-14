package iuh.fit.edu.vn.order_service.services;

import iuh.fit.edu.vn.order_service.dtos.CustomerDTO;
import iuh.fit.edu.vn.order_service.dtos.ProductDTO;
import iuh.fit.edu.vn.order_service.entities.Order;
import iuh.fit.edu.vn.order_service.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public Order create(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (order.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if (order.getProductId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if (order.getQuantity() == null || order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (order.getTotalPrice() == null || order.getTotalPrice() < 0) {
            throw new IllegalArgumentException("Total price must be non-negative");
        }

        // Gọi Customer Service qua Eureka
        String customerServiceUrl = "http://CUSTOMER-SERVICE/customers/" + order.getCustomerId();
        CustomerDTO customer = restTemplate.getForObject(customerServiceUrl, CustomerDTO.class);
        if (customer == null) {
            throw new IllegalArgumentException("Customer with id " + order.getCustomerId() + " not found");
        }

        // Gọi Product Service qua Eureka
        String productServiceUrl = "http://PRODUCT-SERVICE/products/" + order.getProductId();
        ProductDTO product = restTemplate.getForObject(productServiceUrl, ProductDTO.class);
        if (product == null) {
            throw new IllegalArgumentException("Product with id " + order.getProductId() + " not found");
        }
        if (product.getStock() < order.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock for product " + product.getName());
        }

        // Giảm stock trong Product Service
        product.setStock(product.getStock() - order.getQuantity());
        restTemplate.put("http://PRODUCT-SERVICE/products/" + product.getId(), product);

        order.setId(null); // Đảm bảo thêm mới
        return repository.save(order);
    }

    public Order getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Order update(Long id, Order order) {
        Order existingOrder = getById(id);
        if (existingOrder == null) {
            throw new IllegalArgumentException("Order with id " + id + " not found");
        }
        existingOrder.setCustomerId(order.getCustomerId());
        existingOrder.setProductId(order.getProductId());
        existingOrder.setQuantity(order.getQuantity());
        existingOrder.setTotalPrice(order.getTotalPrice());
        return repository.save(existingOrder);
    }

    public void delete(Long id) {
        Order order = getById(id);
        if (order == null) {
            throw new IllegalArgumentException("Order with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
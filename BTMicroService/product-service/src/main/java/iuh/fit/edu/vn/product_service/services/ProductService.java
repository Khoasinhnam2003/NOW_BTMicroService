package iuh.fit.edu.vn.product_service.services;

import iuh.fit.edu.vn.product_service.entities.Product;
import iuh.fit.edu.vn.product_service.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product create(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice() < 0) {
            throw new IllegalArgumentException("Product price must be non-negative");
        }
        if (product.getStock() == null || product.getStock() < 0) {
            throw new IllegalArgumentException("Product stock must be non-negative");
        }
        product.setId(null); // Đảm bảo thêm mới
        return repository.save(product);
    }

    public Product getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product update(Long id, Product product) {
        Product existingProduct = getById(id);
        if (existingProduct == null) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStock(product.getStock());
        return repository.save(existingProduct);
    }

    public void delete(Long id) {
        Product product = getById(id);
        if (product == null) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
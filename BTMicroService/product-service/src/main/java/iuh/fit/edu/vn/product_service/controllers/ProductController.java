package iuh.fit.edu.vn.product_service.controllers;

import iuh.fit.edu.vn.product_service.entities.Product;
import iuh.fit.edu.vn.product_service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.create(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    private static int accessCounter = 0;

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam(required = false) Boolean reset) {
        if (Boolean.TRUE.equals(reset)) {
            accessCounter = 0;
            return ResponseEntity.ok("✅ Đã reset số lần truy cập");
        }

        accessCounter++;
        if (accessCounter < 4) {
            return new ResponseEntity<>("⚠️ Bạn cần thử lại. Lần hiện tại: " + accessCounter, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<Product> products = productService.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.update(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

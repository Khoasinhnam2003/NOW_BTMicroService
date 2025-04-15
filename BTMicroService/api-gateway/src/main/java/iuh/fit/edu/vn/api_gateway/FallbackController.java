package iuh.fit.edu.vn.api_gateway;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/product")
    public ResponseEntity<String> productFallback() {
        return ResponseEntity.ok("Product service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/order")
    public ResponseEntity<String> orderFallback() {
        return ResponseEntity.ok("Order service is currently unavailable. Please try again later.");
    }

    @GetMapping("/fallback/customer")
    public ResponseEntity<String> customerFallback() {
        return ResponseEntity.ok("Customer service is currently unavailable. Please try again later.");
    }
}

package iuh.fit.edu.vn.product_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    // Trả về thành công nhanh
    @GetMapping("/products/ok")
    public String ok() {
        return "Product service is working!";
    }

    // Trả về lỗi 500 (để test Retry + CircuitBreaker)
    @GetMapping("/products/error")
    public String error() {
        throw new RuntimeException("Simulated error from Product service");
    }

    // Delay 5s (để test TimeLimiter)
    @GetMapping("/products/slow")
    public String slow() throws InterruptedException {
        Thread.sleep(5000); // Delay 5 giây
        return "Slow response from Product service";
    }
}

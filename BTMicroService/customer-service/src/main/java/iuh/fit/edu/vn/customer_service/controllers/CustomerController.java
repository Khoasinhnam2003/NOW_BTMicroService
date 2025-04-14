package iuh.fit.edu.vn.customer_service.controllers;

import iuh.fit.edu.vn.customer_service.entities.Customer;
import iuh.fit.edu.vn.customer_service.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Integer id) {
        Customer c = service.getById(id);
        return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    @PostMapping("/update")
    public Customer create(@RequestBody Customer customer) {
        return service.create(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = service.create(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
}

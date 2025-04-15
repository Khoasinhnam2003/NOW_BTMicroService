package iuh.fit.edu.vn.payment_service.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String status; // e.g., "PAID", "UNPAID", "REFUNDED"
}

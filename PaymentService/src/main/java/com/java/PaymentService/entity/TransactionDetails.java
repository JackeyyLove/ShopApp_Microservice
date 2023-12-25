package com.java.PaymentService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.name;

@Entity
@Table(name = "transaction_details")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "mode")
    private String paymentMode;
    @Column(name = "reference_number")
    private String referenceNumber;
    @Column(name = "payment_date")
    private Instant paymentDate;
    @Column(name = "status")
    private String paymentStatus;
    private Long amount;
}

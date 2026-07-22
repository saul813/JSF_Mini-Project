package com.example;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer_feedback")
public class FeedbackEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "comments", length = 500)
    private String comments;

    // Default constructor required by JPA
    public FeedbackEntity() {}

    public FeedbackEntity(String customerName, String comments) {
        this.customerName = customerName;
        this.comments = comments;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}

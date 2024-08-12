package com.galape.tusuper.entities;

import java.time.LocalDateTime;
import java.util.Map;

import com.galape.tusuper.enums.PaymentMethod;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyJoinColumn;

@Entity
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @ElementCollection
    @CollectionTable(name = "order_product", joinColumns = @JoinColumn(name = "clientOrder_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> items;
    private Double total;
    public ClientOrder() {
    }
    public ClientOrder(String id, LocalDateTime dateTime, PaymentMethod paymentMethod, Map<Product, Integer> items,
            Double total) {
        this.id = id;
        this.dateTime = dateTime;
        this.paymentMethod = paymentMethod;
        this.items = items;
        this.total = total;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public Map<Product, Integer> getItems() {
        return items;
    }
    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
}

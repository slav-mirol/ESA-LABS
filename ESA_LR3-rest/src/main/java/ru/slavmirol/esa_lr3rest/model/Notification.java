package ru.slavmirol.esa_lr3rest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(name = "condition_details", columnDefinition = "TEXT")
    private String conditionDetails;

   public Notification(String email, String conditionDetails) {
        this.email = email;
        this.conditionDetails = conditionDetails;
    }

    public Notification() {

    }
    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConditionDetails() {
        return conditionDetails;
    }

    public void setConditionDetails(String conditionDetails) {
        this.conditionDetails = conditionDetails;
    }
}

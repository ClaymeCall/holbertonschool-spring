package com.holberton.realestate.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tenants")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String name;

    @Column(nullable = false, unique = true) private String email;

    @Column(nullable = false) private int monthlyIncome;

    protected Tenant() {}

    public Tenant(String name, String email, int monthlyIncome) {
        this.name = name;
        this.email = email;
        this.monthlyIncome = monthlyIncome;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(int monthlyIncome) { this.monthlyIncome = monthlyIncome; }

}

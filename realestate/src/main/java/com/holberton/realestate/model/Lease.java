package com.holberton.realestate.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leases")
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false) private int durationMonths;
    @Column(nullable = false) private int monthlyRent;
    @Column(nullable = false) private boolean active;

    protected Lease() {}

    public Lease(Property property, Tenant tenant, int durationMonths, int monthlyRent) {
        this.property = property;
        this.tenant = tenant;
        this.durationMonths = durationMonths;
        this.monthlyRent = monthlyRent;
        this.active = true;
    }

    public Long getId() { return id; }
    public Property getProperty() { return property; }
    public Tenant getTenant() { return tenant; }
    public int getDurationMonths() { return durationMonths; }
    public int getMonthlyRent() { return monthlyRent; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

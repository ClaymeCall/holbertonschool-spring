package com.holberton.realestate.model;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) private String reference;

    @Embedded
    private Address address;

    @Column(nullable = false) private int surface;
    @Column(nullable = false) private int monthlyRent;
    @Column(nullable = false) private boolean rented;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) private EnergyClass energyClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    protected Property() {}

    public Property(String reference, Address address, int surface, int monthlyRent,
        EnergyClass energyClass) {
        this.reference = reference;
        this.address = address;
        this.surface = surface;
        this.monthlyRent = monthlyRent;
        this.energyClass = energyClass;
    }

    public Long getId() { return id; }

    public String getReference() { return reference; }

    public Address getAddress() { return address; }

    public void setAddress(Address address) { this.address = address; }

    public int getSurface() { return surface; }

    public void setSurface(int surface) { this.surface = surface; }

    public int getMonthlyRent() { return monthlyRent; }

    public void setMonthlyRent(int monthlyRent) { this.monthlyRent = monthlyRent; }

    public EnergyClass getEnergyClass() { return energyClass; }

    public void setEnergyClass(EnergyClass energyClass) { this.energyClass = energyClass; }

    public Owner getOwner() { return owner; }

    public void setOwner(Owner owner) { this.owner = owner; }

    public boolean isRented() { return rented; }

    public void setRented(boolean rented) { this.rented = rented; }
}

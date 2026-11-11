package com.holberton.realestate.model;

import jakarta.persistence.*;

@Entity
@Table(name = "properties")
public class Property {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String reference;

  @Embedded
  private Address address;

  private int surface;
  private int monthlyRent;

  @Enumerated(EnumType.STRING)
  private EnergyClass energyClass;

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
}

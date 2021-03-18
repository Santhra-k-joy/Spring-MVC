package com.epsilon.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Data
@Table(name="PRODUCTS")
@Entity
public class Product {
	@Id
private int id;
private String name;
private String description;
private String brand;
private String category;

private String picture;
@Column(name="quantity_per_unit")
private String quantityPerUnit;
@Column(name="unit_price")
private double unitPrice;
private double discount;
}

package org.api.java.api_v2_2826502.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "products")

public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "El nombre No debe tener espacios en blanco")
  @NotEmpty(message = "El nombre No puede estar vacio ")
  private String name;

  @Size(min = 100, max = 500, message = "El numero de caracteries excritos estan fuera de rango 100 - 500 caracteres")
  @NotEmpty(message = "No deben estar vacio")
  private String description;

  // @NotNull
  // @Min(50)
  private Double price;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}

package com.Inventario.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor // Constructor vacio
@AllArgsConstructor //Constructor
@ToString //metodo string
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private Double precio;
    private Integer existencia;
}

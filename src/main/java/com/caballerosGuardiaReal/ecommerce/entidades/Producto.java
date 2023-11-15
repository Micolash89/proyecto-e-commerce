package com.caballerosGuardiaReal.ecommerce.entidades;

import com.caballerosGuardiaReal.ecommerce.enumeraciones.Condicion;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nombre;
    private Double precio;
    private String descripcion;
    private String EAN;
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private Condicion condicion;

    @ManyToOne
    private Categoria categoria;

    @OneToOne
    private Imagen imagen;

    @ManyToOne
    private Fabricante fabricante;

    private Boolean estado;//activo: true//no activo: false
    
}

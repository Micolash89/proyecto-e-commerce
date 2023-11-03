package com.caballerosGuardiaReal.ecommerce.entidades;

import com.caballerosGuardiaReal.ecommerce.enumeraciones.Condicion;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Producto {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;
    private Double precio;
    private String Descripcion;
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

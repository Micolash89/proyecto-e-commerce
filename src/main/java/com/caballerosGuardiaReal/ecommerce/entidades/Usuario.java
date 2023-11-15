package com.caballerosGuardiaReal.ecommerce.entidades;

import com.caballerosGuardiaReal.ecommerce.enumeraciones.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String nombreCompleto;
    private String clave;
    private String email;
    private String direccion;
    private Integer codigoPostal;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    @OneToOne
    private Imagen imagen;
    /*
    @OneToMany
    private Ticket tickets;
    
    @OneToOne
    private Orden orden;
    */
    
}

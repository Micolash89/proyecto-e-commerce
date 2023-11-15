
package com.caballerosGuardiaReal.ecommerce.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Data;

@Entity
@Data
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    
    private String Id;
    
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    
    @OneToOne
    private Orden ordenDeCompra;
    
    private Double precioFinal;
    //private Modo formaDePago; //que modo sea un enum de forma de pagos
    
}

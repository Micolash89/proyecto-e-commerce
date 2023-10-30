
package com.caballerosGuardiaReal.ecommerce.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

// OrdenDeCompra
@Entity
@Data
public class Orden {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    
    private String Id;
    
    @Temporal(TemporalType.DATE)
    private Date fechaPedido;
    
//    @OneToMany
//    private Producto producto;
    
    //private Estado pendiente; //pasa a confirmado cuando se emite el ticket

    
}

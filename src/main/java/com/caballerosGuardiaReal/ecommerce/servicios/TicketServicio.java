
package com.caballerosGuardiaReal.ecommerce.servicios;

import com.caballerosGuardiaReal.ecommerce.entidades.Orden;
import com.caballerosGuardiaReal.ecommerce.entidades.Producto;
import com.caballerosGuardiaReal.ecommerce.entidades.Ticket;
import com.caballerosGuardiaReal.ecommerce.repositorios.TicketRepositorio;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServicio {
    
    @Autowired
    private TicketRepositorio ticketRepositorio;
    
    @Transactional
    public Ticket crearTicket(Orden orden){
        Ticket ticket = new Ticket();
        ticket.setOrdenDeCompra(orden);
        List<Producto> productos = orden.getProducto();
        
        Double precioFinal = 0.0;
                
        for (Producto producto : productos) {
            precioFinal = precioFinal + producto.getPrecio();
        }
        
        ticket.setPrecioFinal(precioFinal);
        
        ticket.setFechaEntrega(new Date());
        
        return ticketRepositorio.save(ticket);
    }
    
}

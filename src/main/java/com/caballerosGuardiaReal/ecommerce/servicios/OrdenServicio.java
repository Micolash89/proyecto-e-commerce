
package com.caballerosGuardiaReal.ecommerce.servicios;

import com.caballerosGuardiaReal.ecommerce.entidades.Orden;
import com.caballerosGuardiaReal.ecommerce.entidades.Producto;
import com.caballerosGuardiaReal.ecommerce.repositorios.OrdenRepositorio;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenServicio {
    
    @Autowired
    private OrdenRepositorio ordenRepositorio;
    
    
    @Transactional
    public Orden crearOrden(Producto producto){
       
        Orden orden = new Orden();
        orden.setFechaPedido(new Date());
        orden.getProducto().add(producto);
        
        return ordenRepositorio.save(orden);
    }
    
     @Transactional
     public Orden actualizarOrden(String idOrden, Producto producto){
         
         Optional<Orden> respuesta = ordenRepositorio.findById(idOrden);
         
         if (respuesta.isPresent()) {
             Orden orden = respuesta.get();
             
             orden.getProducto().add(producto);
             
             return ordenRepositorio.save(orden);
         }
         return null;
     }
     
     public Optional<Orden> getOne(String id){
         return ordenRepositorio.findById(id);
     }
    
     
     
}

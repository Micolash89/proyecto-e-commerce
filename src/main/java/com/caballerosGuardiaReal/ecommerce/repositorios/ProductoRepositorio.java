
package com.caballerosGuardiaReal.ecommerce.repositorios;

import com.caballerosGuardiaReal.ecommerce.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, String>{
    
}

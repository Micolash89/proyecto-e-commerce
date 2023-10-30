
package com.caballerosGuardiaReal.ecommerce.repositorios;

import com.caballerosGuardiaReal.ecommerce.entidades.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepositorio extends JpaRepository<Orden, String>{
    
}

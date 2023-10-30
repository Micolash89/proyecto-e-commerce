
package com.caballerosGuardiaReal.ecommerce.repositorios;

import com.caballerosGuardiaReal.ecommerce.entidades.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepositorio extends JpaRepository<Ticket, String>{
    
}


package com.caballerosGuardiaReal.ecommerce.entidades;


import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Matias Insaurralde
 */
@Entity
@Data
public class Imagen {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
   //@GenericGenerator(name = "uuid", type = org.hibernate.id.uuid.UuidGenerator.class)
    private String Id;
     
    private String nombre;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "contenido", columnDefinition = "LONGBLOB")
    private byte[] contenido;

}






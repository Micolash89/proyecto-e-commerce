package com.caballerosGuardiaReal.ecommerce.entidades;


import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import lombok.Data;

@Entity
@Data
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
     
    private String mime;
    
    private String nombre;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "contenido", columnDefinition = "LONGBLOB")
    private byte[] contenido;

}



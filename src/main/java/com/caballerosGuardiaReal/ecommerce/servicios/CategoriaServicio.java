
package com.caballerosGuardiaReal.ecommerce.servicios;

import com.caballerosGuardiaReal.ecommerce.entidades.Categoria;
import com.caballerosGuardiaReal.ecommerce.repositorios.CategoriaRepositorio;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoriaServicio {
    
    
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    
    @Transactional
    public Categoria crearCategoria(String nombre){    
    
        Categoria categoria = new Categoria();
        
        //validar nombre repetidos
        categoria.setNombre(nombre.toLowerCase());
        
        return categoriaRepositorio.save(categoria);
    }
    
    //ver validad
    //ver lanzar throw
    @Transactional
    public Categoria actualizarCategoria(String id, String nombre){
    
        Categoria categoria = null;
        
        Optional <Categoria> respuesta = categoriaRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            categoria = respuesta.get();
            categoria.setNombre(nombre);
            return categoriaRepositorio.save(categoria);
        
        }
        
        return categoria;
        
    }
    
    public List<Categoria> listarCategorias(){
        return categoriaRepositorio.findAll();
    }
    
    public Categoria getOne(String id){
        return categoriaRepositorio.findById(id).get();
    }
    
}
 
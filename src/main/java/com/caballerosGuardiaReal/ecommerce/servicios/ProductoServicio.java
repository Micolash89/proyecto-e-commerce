
package com.caballerosGuardiaReal.ecommerce.servicios;

import com.caballerosGuardiaReal.ecommerce.entidades.Categoria;
import com.caballerosGuardiaReal.ecommerce.entidades.Fabricante;
import com.caballerosGuardiaReal.ecommerce.entidades.Imagen;
import com.caballerosGuardiaReal.ecommerce.entidades.Producto;
import com.caballerosGuardiaReal.ecommerce.enumeraciones.Condicion;
import com.caballerosGuardiaReal.ecommerce.excepciones.MiException;
import com.caballerosGuardiaReal.ecommerce.repositorios.CategoriaRepositorio;
import com.caballerosGuardiaReal.ecommerce.repositorios.FabricanteRepositorio;
import com.caballerosGuardiaReal.ecommerce.repositorios.ProductoRepositorio;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ProductoServicio {
    
    @Autowired
    private ProductoRepositorio productoRepositorio;
    
    @Autowired
    private FabricanteRepositorio fabricanteoRepositorio;
    
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    
    @Autowired
    private FabricanteServicio fabricanteServicio;
    
    @Autowired
    private CategoriaServicio categoriaServicio;
    
    @Autowired
    private ImagenServicio imagenServicio;
    
    @Transactional
    //falta agrgar miException
    public Producto crearProducto(String nombre, Double precio, String descripcion, Integer stock, Condicion condicion, String idCategoria , MultipartFile archivo, String idFabricante) throws MiException{
        
        Producto producto = new Producto();
        
        //falta validar
        //funcion recurrente que escriba un factorial, programacion recurrente
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setStock(stock);
        producto.setCondicion(condicion);
        producto.setEstado(Boolean.TRUE);
        
        Optional <Fabricante> fabricanteRespuesta = fabricanteoRepositorio.findById(idFabricante);
        Optional <Categoria> categoriaRespuesta = categoriaRepositorio.findById(idCategoria);
        
        Fabricante fabricante = new Fabricante();
        
        Categoria categoria = new Categoria();
        
        if(fabricanteRespuesta.isPresent()){
            fabricante = fabricanteRespuesta.get();
        }else{
            fabricante = fabricanteServicio.crearFabricante(idFabricante);
        }
        
        if(categoriaRespuesta.isPresent()){
            categoria = categoriaRespuesta.get();
        }else{
            categoria = categoriaServicio.crearCategoria(idCategoria);
        }
        
        producto.setFabricante(fabricante);
        producto.setCategoria(categoria);
        
        Imagen imagen = null;
        //ver lo de el try catch
        try {
            imagen = imagenServicio.guardar(archivo);
        } catch (IOException | MiException  ex) {
            Logger.getLogger(ProductoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        producto.setImagen(imagen);
        
        return productoRepositorio.save(producto);
    }
    
    @Transactional
    //agregar el miException
    //voy a listar la categoria y el fabricante
    public Producto actualizar(String idProducto ,String nombre, Double precio, String descripcion, Integer stock, Condicion condicion, String idCategoria , MultipartFile archivo, String idFabricante, Boolean estado){
    //validar?
    
        Optional <Producto> respuesta = productoRepositorio.findById(idProducto);
        
        Optional <Categoria> categoriaRespuesta = categoriaRepositorio.findById(idFabricante);
        
        Optional <Fabricante> fabricanteRespuesta = fabricanteoRepositorio.findById(idCategoria);
        
        Categoria categoria;
        
        Fabricante fabricante;
        
        if(categoriaRespuesta.isPresent()){
            
            categoria = categoriaRespuesta.get();
            
        }else{
            //ver despues
            categoria = categoriaServicio.crearCategoria(idCategoria);
        }
        
        if(fabricanteRespuesta.isPresent()){
            fabricante = fabricanteRespuesta.get();
        }else{
            //ver despues
            fabricante = fabricanteServicio.crearFabricante(idFabricante);
        }
        
        
        if(respuesta.isPresent()){
            Producto producto = respuesta.get();
            
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setCategoria(categoria);
            producto.setFabricante(fabricante);
            producto.setPrecio(precio);
            producto.setEstado(estado);
            String idImagen = null;
            
            if (producto.getImagen() != null) {
                idImagen = producto.getImagen().getId();
            }
            
            Imagen imagen = null;
            //ver el try catch
            try {
                imagen = imagenServicio.actualizar(archivo, idImagen);
            } catch (IOException |  MiException ex) {
                Logger.getLogger(ProductoServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            producto.setImagen(imagen);
            
            return productoRepositorio.save(producto);
            
        }
        return null;
    }
    
    public Producto getOne(String id){
        return productoRepositorio.findById(id).get();
    }
    
    public List<Producto> listarProductos(){
        
        return productoRepositorio.findAll();
    
    }
    
    
    
    
}

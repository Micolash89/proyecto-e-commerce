package com.caballerosGuardiaReal.ecommerce.controladores;

import com.caballerosGuardiaReal.ecommerce.entidades.Imagen;
import com.caballerosGuardiaReal.ecommerce.servicios.ImagenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Matias Insaurralde
 */

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {


    @Autowired
    private ImagenServicio imagenServicio;
    
<<<<<<< HEAD
//    @GetMapping("/producto/{id}")
//   public ResponseEntity<byte[]> ImagenUsuario(@PathVariable String id){
//       Usuario usuario = usuarioServicio.getOne(id);
//        
//       byte[] imagen = usuario.getImagen().getContenido();
//        
//       HttpHeaders headers = new HttpHeaders();
//       headers.setContentType(MediaType.IMAGE_JPEG);
//        
//       return new ResponseEntity<>(imagen,headers,HttpStatus.OK);
//    }
=======

>>>>>>> master
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable String id) {

        Imagen imagen = imagenServicio.obtenerImagenPorId(id);

        if (imagen != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Ajusta el tipo de contenido según el tipo de imagen
            return new ResponseEntity<>(imagen.getContenido(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
}

package com.caballerosGuardiaReal.ecommerce.servicios;

import com.caballerosGuardiaReal.ecommerce.entidades.Imagen;
import com.caballerosGuardiaReal.ecommerce.excepciones.MiException;
import com.caballerosGuardiaReal.ecommerce.repositorios.ImagenRepositorio;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Matias Insaurralde
 */
@Service
public class ImagenServicio {

    @Autowired
    private ImagenRepositorio imagenRepositorio;

    public Imagen guardar(MultipartFile archivo) throws MiException, IOException {

        if (archivo != null) {

            try {

                Imagen imagen = new Imagen();

                imagen.setNombre(archivo.getName());

                imagen.setMime(archivo.getContentType());

                imagen.setContenido(archivo.getBytes());

                return imagenRepositorio.save(imagen);

            } catch (IOException e) {

                System.out.println(e.getMessage());
                throw new MiException("Error al guardar la imagen!");

            }

        }

        return null;

    }

    public Imagen actualizar(MultipartFile archivo, String idImagen) throws IOException, MiException {

        if (archivo != null) {

            try {

                Imagen imagen = new Imagen();

                if (idImagen != null) {

                    Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);

                    if (respuesta.isPresent()) {

                        imagen = respuesta.get();

                    }

                }

                imagen.setNombre(archivo.getName());

                imagen.setContenido(archivo.getBytes());

                imagen.setMime(archivo.getContentType());

                return imagenRepositorio.save(imagen);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new MiException("Error al actualizar la imagen!");

            }

        }

        return null;

    }

    public Imagen obtenerImagenPorId(String id) {
        return imagenRepositorio.findById(id).orElse(null);
    }

//    public boolean Eliminar(String idImagen) throws IOException, MiException{
//    
//        
//        try {
//            
//              if (idImagen != null && !idImagen.trim().isEmpty()  ) {
//            
//          Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);
//     
//          if (respuesta.isPresent()) {
//    
//         imagenRepositorio.delete(respuesta.get());
//
//         return true;
//          }
//            
//              }
//  
//            
//        } catch (Exception e) {
// 
//               System.out.println(e.getMessage());
//                throw  new MiException("Error al eliminar la imagen!");
//        
//        }
//        
//      
//          
// 
//    
//    return false;
//    }
//    
}

package com.caballerosGuardiaReal.ecommerce.servicios;

import com.caballerosGuardiaReal.ecommerce.entidades.Imagen;
import com.caballerosGuardiaReal.ecommerce.entidades.Usuario;
import com.caballerosGuardiaReal.ecommerce.enumeraciones.Rol;
import com.caballerosGuardiaReal.ecommerce.excepciones.MiException;
import com.caballerosGuardiaReal.ecommerce.repositorios.UsuarioRepositorio;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio{
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    
    @Autowired
    private ImagenServicio imagenServicio;
    
    
    @Transactional//Falta MiException
    public void crearUsuario(MultipartFile archivo, String nombreCompleto, String clave, String email, String direccion, Integer codigoPostal)throws MiException {
        
        //validar(nombreCompleto, clave, email, direccion, codigoPostal);

        Usuario u = new Usuario();
        
        u.setNombreCompleto(nombreCompleto);
        u.setClave(clave);
        u.setEmail(email);
        u.setDireccion(direccion);
        u.setCodigoPostal(codigoPostal);
        u.setRol(Rol.CLIENTE);
        
        
//        Imagen imagen = imagenServicio.guardar(archivo);
//        u.setImagen(imagen);
        
        
        usuarioRepositorio.save(u);
    }
    
    @Transactional//Falta MiException
    public void modificarUsuario(MultipartFile archivo, String id, String nombreCompleto, String clave, String email, String direccion, Integer codigoPostal) throws MiException, IOException {
        
        validar(nombreCompleto, clave, email, direccion, codigoPostal);
        
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            Usuario u = respuesta.get();
            
            u.setNombreCompleto(nombreCompleto);
            u.setClave(clave);
            u.setEmail(email);
            u.setDireccion(direccion);
            u.setCodigoPostal(codigoPostal);
            
            String idImagen = null;
            
            if (u.getImagen() != null) {
                idImagen = u.getImagen().getId();
            }
            
            Imagen imagen = imagenServicio.actualizar(archivo, idImagen);
            
            u.setImagen(imagen);
            
            usuarioRepositorio.save(u);
        }
    }
    
    //Agregar throws MiException
    public void validar(String nombreCompleto, String clave, String email, String direccion, Integer codigoPostal) throws MiException{
         if (nombreCompleto.isEmpty() || nombreCompleto == null) {
            throw new MiException("El nombre no puede estar vacío");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("El email no puede estar vacío");
        }
        if (clave.isEmpty() || clave == null || clave.length() <= 5) {
            throw new MiException("La clave no puede estar vacío");
        }
        if (direccion.isEmpty() || direccion == null) {
            throw new MiException("La dirección no puede estar vacio");
        }
        if(codigoPostal == null){
            throw new MiException("El código postal no puede estar vacío");
        }
    }
    
    public Usuario getOne(String id){
        return usuarioRepositorio.findById(id).get();
    }
    
    public List<Usuario> getAll(){
        return usuarioRepositorio.findAll();
    }
    
    @Transactional
    public void modificarRol(String id){
        
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Usuario u = respuesta.get();
            if(u.getRol().equals("ADMIN"))
                u.setRol(Rol.CLIENTE);
            else if(u.getRol().equals("CLIENTE"))
                u.setRol(Rol.ADMIN);
        }
    }
    
//     @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
//
//        if (usuario != null) {
//
//            List<GrantedAuthority> permisos = new ArrayList();
//
//            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
//
//            permisos.add(p);
//
//            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//
//            HttpSession session = attr.getRequest().getSession(true);
//
//            session.setAttribute("usuariosession", usuario);
//
//            return new User(usuario.getEmail(), usuario.getClave(), permisos);
//        } else {
//            return null;
//        }
//}
}

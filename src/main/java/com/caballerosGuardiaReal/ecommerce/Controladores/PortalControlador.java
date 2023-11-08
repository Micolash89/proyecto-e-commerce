package com.caballerosGuardiaReal.ecommerce.Controladores;

import com.caballerosGuardiaReal.ecommerce.excepciones.MiException;
import com.caballerosGuardiaReal.ecommerce.servicios.UsuarioServicio;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/registrar")
    public String regristar(){
        
        return "formulario.html";
        
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombreCompleto, @RequestParam String clave, @RequestParam(required=false) MultipartFile  archivo, @RequestParam String email,@RequestParam String direccion,@RequestParam Integer codigoPostal, ModelMap modelo) throws IOException{
        
        try {
            
        usuarioServicio.crearUsuario(archivo, nombreCompleto, clave, email, direccion, codigoPostal);
        
        modelo.put("exito", "Registro exitoso.");
        
        } catch(MiException e){
            
            modelo.put("error", e.getMessage());
            return "formulario.html";
            
        }
        
        return "index.html";
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "usuario o contraseña invalida");
        }

        return "login.html";
    }
}

package com.caballerosGuardiaReal.ecommerce.Controladores;

import com.caballerosGuardiaReal.ecommerce.entidades.Usuario;
import com.caballerosGuardiaReal.ecommerce.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard.html";
    }
    
    @GetMapping("/usuarios")
    public String listarUsuarios(ModelMap modelo){
        List<Usuario> listaUsuarios = usuarioServicio.getAll();
        modelo.put("usuarios", listaUsuarios);
        return "usuarios_lista.html";
    }
    
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id){
        
        usuarioServicio.modificarRol(id);
        
        return "redirect:/admin/usuarios";
    
    }
    
}

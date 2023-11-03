
package com.caballerosGuardiaReal.ecommerce.controladores;

import com.caballerosGuardiaReal.ecommerce.servicios.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/categoria")
public class CategoriaControlador {
    
    @Autowired
    CategoriaServicio categoriaServicio;
    
    @GetMapping("/registrar")
    public String registrar(ModelMap modelo){
    
        return "categoria_form.html";
    
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre , ModelMap modelo){
    
        try {
            //lanza una exception el meetodo del servicio
            categoriaServicio.crearCategoria(nombre);
            modelo.put("exito","se agrego una categoria exitosamente");
        } catch (Exception e) {
            modelo.put("error",e.getMessage());
            return "categoria_form.html";
        }
    
    return "index.html";
    }
    
}

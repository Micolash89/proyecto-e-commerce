
package com.caballerosGuardiaReal.ecommerce.controladores;

import com.caballerosGuardiaReal.ecommerce.servicios.FabricanteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fabricante")
public class FabricanteControlador {
    
    @Autowired
    private FabricanteServicio fabricanteServicio;
    
    @GetMapping("/registrar")
    public String registrar(){
    
        return "fabricante_form.html";
        
    }
    
    //metodo  fabricante servicio lanza una exception mi exception
    //validar
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo){
    
        try {
            ///genera un miexception
            fabricanteServicio.crearFabricante(nombre);
            modelo.put("exito", "el fabricante fue agregado correctamente");
        } catch (Exception e) {
            
            modelo.put("error", e.getMessage());
            return "fabricante_form.html";
            
        }
        
        return "index.html";
        
    }
    
    //@GetMapping("/lista")
    
    
}

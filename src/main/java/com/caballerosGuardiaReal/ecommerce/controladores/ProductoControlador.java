package com.caballerosGuardiaReal.ecommerce.controladores;

import com.caballerosGuardiaReal.ecommerce.entidades.Categoria;
import com.caballerosGuardiaReal.ecommerce.entidades.Fabricante;
import com.caballerosGuardiaReal.ecommerce.entidades.Producto;
import com.caballerosGuardiaReal.ecommerce.enumeraciones.Condicion;
import com.caballerosGuardiaReal.ecommerce.servicios.CategoriaServicio;
import com.caballerosGuardiaReal.ecommerce.servicios.FabricanteServicio;
import com.caballerosGuardiaReal.ecommerce.servicios.ProductoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/producto")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Autowired
    private FabricanteServicio fabricanteServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap modelo) {

        List<Categoria> categorias = categoriaServicio.listarCategorias();
        List<Fabricante> fabricante = fabricanteServicio.ListarFabricantes();

        modelo.addAttribute("categorias", categorias);
        modelo.addAttribute("fabricantes", fabricante);

        return "producto_form.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam Double precio, @RequestParam(required = false) String descripcion,
            @RequestParam Integer stock, @RequestParam String condicion, @RequestParam(required = false) MultipartFile archivo,
            @RequestParam String idCategoria, @RequestParam String idFabricante,@RequestParam String EAN,  ModelMap modelo) {
        System.out.println("----------------------------------------------------");
        try {
            //adentro de producto servicio valida y pouede dar un error
            productoServicio.crearProducto(nombre, precio, descripcion, stock,Condicion.valueOf(condicion), idCategoria, archivo, idFabricante,EAN);
        } catch (Exception e) {
            //modelo.put("error",ex.getMessage());
            List<Categoria> categorias = categoriaServicio.listarCategorias();
            List<Fabricante> fabricante = fabricanteServicio.ListarFabricantes();

            modelo.addAttribute("categorias", categorias);
            modelo.addAttribute("fabricantes", fabricante);

            return "producto_form.html";
        }

        ///nose a donde retornar
        return "index.html";

    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Producto> productos = productoServicio.listarProductos();

        modelo.addAttribute("productos", productos);

        return "producto_list.html";

    }

    @GetMapping("modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {

        Producto producto = productoServicio.getOne(id);
        List<Categoria> categorias = categoriaServicio.listarCategorias();
        List<Fabricante> fabricantes = fabricanteServicio.ListarFabricantes();

        modelo.addAttribute("producto", producto);
        modelo.addAttribute("categorias", categorias);
        modelo.addAttribute("fabricante", fabricantes);

        return "producto_modificar.html";

    }

    //@RequestParam String nombre, @RequestParam Double precio, @RequestParam(required = false) String descripcion,
    //@RequestParam Integer stock, @RequestParam Condicion condidion, @RequestParam(required = false) MultipartFile archivo,
    //@RequestParam String idCategoria, @RequestParam String idFabricante, ModelMap modelo) {
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, @RequestParam String nombre, @RequestParam Double precio, @RequestParam(required = false) String descripcion,
            @RequestParam Integer stock, @RequestParam String condicion, @RequestParam(required = false) MultipartFile archivo,
            @RequestParam String idCategoria, @RequestParam String idFabricante, @RequestParam Boolean estado, @RequestParam String EAN, ModelMap modelo) {

        try {
            //ver lo de estado
            productoServicio.actualizar(idFabricante, nombre, precio, descripcion, stock,Condicion.valueOf(condicion) , idCategoria, archivo, idFabricante, estado, EAN);
            return "redirect:../lista";
            
        } catch (Exception e) {
            Producto producto = productoServicio.getOne(id);
            List<Categoria> categorias = categoriaServicio.listarCategorias();
            List<Fabricante> fabricantes = fabricanteServicio.ListarFabricantes();

            modelo.addAttribute("producto", producto);
            modelo.addAttribute("categorias", categorias);
            modelo.addAttribute("fabricantes", fabricantes);

            return "producto_modificar.html";
        }

    }

    @GetMapping("/alta_baja/{id}")
    public String AltaBaja(String id) {
        
        productoServicio.cambiarEstado(id);
        
        return "redirect:/producto/lista";
        
    }

}

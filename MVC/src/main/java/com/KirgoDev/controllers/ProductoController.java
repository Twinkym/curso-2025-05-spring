package com.KirgoDev.controllers;

import com.KirgoDev.dto.ProductoFiltroDTO;
import com.KirgoDev.entities.Producto;
import com.KirgoDev.repositories.CategoriaRepository;
import com.KirgoDev.repositories.ProductoRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class ProductoController {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoController(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // anotación que mapea las peticiones GET a la URL "/productos"
    @GetMapping("/productos") // http://localhost:8080/productos
    public String findAll(@RequestParam(required = false) String nombre, @RequestParam(required = false) Long categoriaId, @RequestParam(required = false) Boolean disponible, Model model) {

        // Enviar valores al DTO para que el formulario los mantenga.
        ProductoFiltroDTO filtro = new ProductoFiltroDTO();
        filtro.setNombre(nombre);
        filtro.setCategoriaId(categoriaId);
        filtro.setDisponible(disponible);

        // crear una lista con todos los productos
        List<Producto> productos = productoRepository.findByFiltro(nombre, categoriaId, disponible);
        productos = productoRepository.findAll();
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", filtro);
        model.addAttribute("categorias", categoriaRepository.findAll());

        return "producto-list";
    }

    // Show product detail by ID
    @GetMapping("/productos/{id}")
    public String ShowProductDetail(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con esa ID: " + id));
        model.addAttribute("producto", producto);
        return "producto-detail"; // resources/templates/producto-detail.html
    }

    // Edit products
    @GetMapping("/productos/editar/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con esa ID: " + id));
        if (producto == null) {
            model.addAttribute("error", "Producto no encontrado con esa ID: " + id);
            return "redirect:/productos";
        }
        model.addAttribute("producto", producto);
        return "producto-edit";  // resources/templates/producto-edit.html
    }

    // Delete products
    @GetMapping("/productos/editar/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con esa ID: " + id));
        if (producto != null) {
            productoRepository.delete(producto);
        } else {
            model.addAttribute("error", "Producto no encontrado con esa ID: " + id);
        }
        return "redirect:/productos";
    }
}
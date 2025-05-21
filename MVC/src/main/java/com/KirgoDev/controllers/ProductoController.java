package com.KirgoDev.controllers;

import com.KirgoDev.entities.Producto;
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

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // anotación que mapea las peticiones GET a la URL "/productos"
    @GetMapping("/productos") // http://localhost:8080/productos
    public String findAll(Model model) {
// crear una lista con todos los productos
        List<Producto> productos = productoRepository.findAll();
        model.addAttribute("productos", productos);

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
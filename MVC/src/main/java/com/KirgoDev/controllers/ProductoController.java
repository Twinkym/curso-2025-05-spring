package main.java.com.KirgoDev.controllers;

import main.java.com.KirgoDev.dto.ProductoFiltroDTO;
import main.java.com.KirgoDev.entities.Producto;
import main.java.com.KirgoDev.repositories.CategoriaRepository;
import main.java.com.KirgoDev.repositories.ProductoRepository;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


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
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", filtro);
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("producto", new Producto(String.format("Nuevo producto %s", productos.size() + 1), "", 0.0, 0, null));

        return "producto-list";
    }

    // POST - Guardar nuevo producto desde el modal.
    @PostMapping("/productos/nuevo")
    public String saveNewProduct(@Valid @ModelAttribute Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productos", productoRepository.findAll());
            model.addAttribute("categorias", categoriaRepository.findAll());
            model.addAttribute("producto", producto);
            return "producto-list"; // re-render con errores.
        }
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    /**
     * Muestra el detalle de un producto.
     */
    // Show product detail by ID
    @GetMapping("/productos/{id}")
    public String showProductDetail(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con esa ID: " + id));
        model.addAttribute("producto", producto);
        return "producto-detail"; // resources/templates/producto-detail.html
    }

    /**
     * Muestra el formulario para editar un producto.
     */
    // Edit products
    @GetMapping("/productos/editar/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con esa ID: " + id));
        model.addAttribute("producto", producto);
        return "producto-edit";  // resources/templates/producto-edit.html
    }

    /**
     * Actualiza un producto existente.
     */
    // Update products
    @PostMapping("/productos/update/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute Producto producto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("producto", producto);
            return "producto-edit"; // re-render con errores.
        }
        producto.setId(id);
        productoRepository.save(producto);
        return "redirect:/productos";
    }
    // Delete products
    @GetMapping("/productos/eliminar/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con esa ID: " + id));
        productoRepository.delete(producto);
        return "redirect:/productos";
    }

    @PostMapping("/productos/nuevo")
    public String saveNewProduct(@Valid @ModelAttribute Producto producto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("productos", productoRepository.findAll());
            model.addAttribute("categorias", categoriaRepository.findAll());
            model.addAttribute("producto", producto);
            return "Producto-list";
        }
        productoRepository.save(producto);
        redirectAttributes.addFlashAttribute("mensaje", "Producto creado exitosamente");
        redirectAttributes.addFlashAttribute("tipoMensaje", "alert-success");
        return "redirect:/productos";
    }
}
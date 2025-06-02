package com.KirgoDev.config;

import com.KirgoDev.entities.Categoria;
import com.KirgoDev.entities.Producto;
import com.KirgoDev.repositories.CategoriaRepository;
import com.KirgoDev.repositories.ProductoRepository;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public DataInitializer(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create categories
        Categoria frutas = new Categoria("Frutas", "Frutas y verduras");
        Categoria verduras = new Categoria("Verduras", "Frutas y verduras");
        Categoria lacteos = new Categoria("Lacteos", "Lacteos y granos");
        Categoria bebidas = new Categoria("Bebidas", "Bebidas y alimentos");
        Categoria carnicos = new Categoria("Carnicos", "Carnicos y pollo");
        Categoria carnicos2 = new Categoria("Carnicos 2", "Carnicos y pollo");

        // Save categories
        categoriaRepository.saveAll(List.of(frutas, verduras, lacteos, bebidas, carnicos, carnicos2));

        // Create products
        List<Producto> productos = List.of(
            new Producto("Manzana", "Manzana verde", 1.90, 100, frutas),
            new Producto("Pera", "Pera San Juan", 1.60, 100, frutas),
            new Producto("Uva", "Uva roja", 5.00, 100, frutas),
            new Producto("Fresa", "Fresa de Huelva", 8.00, 100, frutas),
            new Producto("Arveja", "Arveja de Madrid", 1.00, 100, frutas),
            new Producto("Naranja", "Naranja de España", 2.50, 100, frutas),
            new Producto("Manzana 2", "Manzana Roja", 1.90, 100, frutas),
            new Producto("Pera 2", "Pera ", 1.60, 100, frutas),
            new Producto("Uva 2", "Uva blanca", 5.00, 100, frutas),
            new Producto("Fresa 2", "Fresón de Mataró", 6.00, 100, frutas),
            new Producto("Costilla C", "Costilla Cerdo troceada", 4.57, 100, carnicos),
            new Producto("Costilla C 2", "Costillar de Cerdo entero", 4.30, 100, carnicos2),
            new Producto("Costilla R", "Costilla de  Res", 6.70, 100, carnicos),
            new Producto("Bistec", "Bistec de ternera 1ª", 18.99, 100, carnicos),
            new Producto("Pechuga", "Pechuga de pollo", 10.00, 100, carnicos2),
            new Producto("Yogur", "Yogur de chocolate", 1.70, 100, lacteos),
            new Producto("Natilla", "Natilla de chocolate", 1.90, 100, lacteos),
            new Producto("Agua", "Agua mineral", 0.50, 100, bebidas),
            new Producto("Cerveza", "Cerveza de Alhambra", 1.40, 100, bebidas),
            new Producto("Cerveza 2", "Cerveza Moritz", 1.60, 100, bebidas),
            new Producto("Cerveza 3", "Cerveza estrella Galicia", 1.90, 100, bebidas)
        );

        productoRepository.saveAll(productos);
        // Save products
        System.out.println("Datos Iniciales cargados Correctamente");

        // Print all categories
        System.out.println("Listado de categorías:");
        categoriaRepository.findAll().forEach(System.out::println);

        // print all products
        System.out.println("Listado de productos:");
        productoRepository.findAll().forEach(System.out::println);

        // print products total stock
        System.out.println("Total stock de productos:");
    }

}

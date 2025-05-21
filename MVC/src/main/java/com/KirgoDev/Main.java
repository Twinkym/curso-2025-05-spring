package com.KirgoDev;

import com.KirgoDev.entities.Categoria;
import com.KirgoDev.entities.Producto;
import com.KirgoDev.repositories.CategoriaRepository;
import com.KirgoDev.repositories.ProductoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // Run the application
        ApplicationContext ctx = SpringApplication.run(Main.class, args);

        // Get repositories (Spring generated)
        CategoriaRepository categoriaRepository = ctx.getBean(CategoriaRepository.class);
        ProductoRepository productoRepository = ctx.getBean(ProductoRepository.class);

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
        Producto manzana = new Producto("Manzana", "Manzana verde", 1.90, 100, frutas);
        Producto pera = new Producto("Pera", "Pera San Juan", 1.60, 100, frutas);
        Producto uva = new Producto("Uva", "Uva roja", 5.00, 100, frutas);
        Producto fresa = new Producto("Fresa", "Fresa de Huelva", 8.00, 100, frutas);
        Producto arveja = new Producto("Arveja", "Arveja de Madrid", 1.00, 100, frutas);
        Producto naranja = new Producto("Naranja", "Naranja de España", 2.50, 100, frutas);
        Producto manzana2 = new Producto("Manzana 2", "Manzana Roja", 1.90, 100, frutas);
        Producto pera2 = new Producto("Pera 2", "Pera ", 1.60, 100, frutas);
        Producto uva2 = new Producto("Uva 2", "Uva blanca", 5.00, 100, frutas);
        Producto fresa2 = new Producto("Fresa 2", "Freson de Mataró", 6.00, 100, frutas);
        Producto CostillaC = new Producto("Costilla C", "Costilla Cerdo troceada", 4.57, 100, carnicos);
        Producto CostillaC2 = new Producto("Costilla C 2", "Costillar de Cerdo entero", 4.30, 100, carnicos2);
        Producto costillaR = new Producto("Costilla R", "Costilla de  Res", 6.70, 100, carnicos);
        Producto bistec = new Producto("Bistec", "Bistec de ternera 1ª", 18.99, 100, carnicos);
        Producto pechuga = new Producto("Pechuga", "Pechuga de pollo", 10.00, 100, carnicos2);
        Producto yogur = new Producto("Yogur", "Yogur de chocolate", 1.70, 100, lacteos);
        Producto natilla = new Producto("Natilla", "Natilla de chocolate", 1.90, 100, lacteos);
        Producto agua = new Producto("Agua", "Agua mineral", 0.50, 100, bebidas);
        Producto cerveza = new Producto("Cerveza", "Cerveza de Alhambra", 1.40, 100, bebidas);
        Producto cerveza2 = new Producto("Cerveza 2", "Cerveza Moritz", 1.60, 100, bebidas);
        Producto cerveza3 = new Producto("Cerveza 3", "Cerveza estralla Galicia", 1.90, 100, bebidas);

        // Save products
        productoRepository.saveAll(List.of(manzana, pera, uva, fresa, arveja, naranja, manzana2, pera2, uva2, fresa2, CostillaC, CostillaC2, costillaR, bistec, pechuga, yogur, natilla, agua, cerveza, cerveza2, cerveza3));

        // Print all categories
        System.out.println("Listado de categorías:");
        categoriaRepository.findAll().forEach(System.out::println);

        // print all products
        System.out.println("Listado de productos:");
        productoRepository.findAll().forEach(System.out::println);

        // print products total stock
        System.out.println("Total stock de productos:");

        // close context
        ctx.close();
    }
}

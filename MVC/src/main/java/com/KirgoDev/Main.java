package main.java.com.KirgoDev;

import main.java.com.KirgoDev.repositories.CategoriaRepository;
import main.java.com.KirgoDev.repositories.ProductoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // Run the application
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        // Get repositories (Spring generated)
        CategoriaRepository categoriaRepository = ctx.getBean(CategoriaRepository.class);
        ProductoRepository productoRepository = ctx.getBean(ProductoRepository.class);
    }
}

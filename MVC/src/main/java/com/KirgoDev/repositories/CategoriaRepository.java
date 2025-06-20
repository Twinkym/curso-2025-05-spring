package main.java.com.KirgoDev.repositories;

import main.java.com.KirgoDev.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    // Search By name ignoring case
    Optional<Categoria> findByNameIgnoreCase(String name);

    // List all categories ordered by name
    List<Categoria> findAllByOrderByNombreAsc();

    // List all categories ordered by name descending
    List<Categoria> findAllByOrderByNombreDesc();

    // List all categories ordered by stock
    List<Categoria> findAllByOrderByStockAsc();

    // List all categories ordered by stock descending
    List<Categoria> findAllByOrderByStockDesc();

    // List all categories ordered by price
    List<Categoria> findAllByOrderByPrecioAsc();

    // List all categories ordered by price descending
    List<Categoria> findAllByOrderByPrecioDesc();

    // List all categories ordered by disponible
    List<Categoria> findAllByOrderByDisponibleAsc();

    // List all categories ordered by disponible descending
    List<Categoria> findAllByOrderByDisponibleDesc();

    // List all categories ordered by id
    List<Categoria> findAllByOrderByIdAsc();

    // List all categories ordered by id descending
    List<Categoria> findAllByOrderByIdDesc();

    // know if there is a category with that name
    boolean existsByNameIgnoreCase(String name);
}
/*
 * @author KirgoDev
 * @version 1.0
 * @created 10-05-2022 10:08:29 a. m.
 * @description Clase que implementa el repositorio de categorías para la base de datos
 * implementa funciones CRUD gracias a JpaRepository, métodos útiles derivados por nombre,
 * posibilidad de asociar o buscar categorías desde Producto.
 *
 */
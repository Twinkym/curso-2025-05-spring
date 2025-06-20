package main.java.com.KirgoDev.repositories;

import main.java.com.KirgoDev.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long>, ProductoRepositoryImpl{

    // Spring boot implementa métodos básicos: save, findById, findAll, deleteById
    // JpaRepository implementa métodos más completos: save, findById, findAll, deleteById, saveAll, deleteAll, count
    // JpaRepository implementa métodos más completos: save, findById, findAll, deleteById, saveAll, deleteAll, count

    // métodos derivados.

    // FindByName ignoring cap
    Producto findByNameIgnoreCase(String name);

    // Find by price minor than
    List<Producto> findByPrecioLessThan(Double precio);

    // Find by price minor than and name like
    List<Producto> findByPrecioLessThanAndNombreLike(Double precio, String nombre);

    // Find products by category name
    List<Producto> findByCategoria_Nombre(String nombre);

    // Find products by category_ID
    List<Producto> findByCategoria_Id(Long id);

    // Find by available stock
    List<Producto> findByStockGreaterThan(Integer stock);

    // Find all By Disponible
    List<Producto> findAllByDisponibleTrue();


    // Exists by name ignoring Capital
    boolean existsByNameIgnoreCase(String name);

    // Count stock
    long countByStock(Integer stock);

    // JPQL Queries
    // Find by name
    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:nombre%")
    List<Producto> findByNombre(@Param("nombre") String nombre);

    // Find by price between
    @Query("SELECT p FROM Producto p WHERE p.precio BETWEEN :precioStart AND :precioEnd")
    List<Producto> findByPrecioBetween(@Param("precioStart") Double precioStart, @Param("precioEnd") Double precioEnd);

    // If the property does not exist, then it will fail to boot.


}

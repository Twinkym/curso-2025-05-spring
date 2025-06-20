package main.java.com.KirgoDev.repositories;

import main.java.com.KirgoDev.entities.Producto;
import java.util.List;

public interface ProductoRepositoryCustom {
    List<Producto> findByFiltro(String nombre, Long categoriaId, Boolean disponible);
}

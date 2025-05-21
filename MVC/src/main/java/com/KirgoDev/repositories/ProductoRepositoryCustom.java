package com.KirgoDev.repositories;

import com.KirgoDev.entities.Producto;
import java.util.List;

public interface ProductoRepositoryCustom {
    List<Producto> findByFiltro(String nombre, Long categoriaId, Boolean disponible);
}

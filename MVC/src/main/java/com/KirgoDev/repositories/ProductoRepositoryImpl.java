package com.KirgoDev.repositories;

import com.KirgoDev.entities.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepositoryImpl implements ProductoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Producto> findByFiltro(String nombre, Long categoriaId, Boolean disponible) {
        StringBuilder query = new StringBuilder("SELECT p FROM Producto p WHERE 1=1");
        List<String> condiciones = new ArrayList<>();
        if (nombre != null && !nombre.isEmpty()) {
            query.append(" AND LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))");
        }
        if (categoriaId != null) {
            query.append(" AND p.categoria.id = :categoriaId");
        }
        if (disponible != null) {
            query.append(" AND p.disponible = :disponible");
        }

        TypedQuery<Producto> typedQuery = entityManager.createQuery(query.toString(), Producto.class);

        if (nombre != null && !nombre.isEmpty()) {
            typedQuery.setParameter("nombre", nombre);
        }
        if (categoriaId != null) {
            typedQuery.setParameter("categoriaId", categoriaId);
        }
        if (disponible != null) {
            typedQuery.setParameter("disponible", disponible);
        }

        return typedQuery.getResultList();
    }
}

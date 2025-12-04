package com.talento.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talento.crud.model.Producto;

public interface ProductoRepository extends JpaRepository< Producto, Long> {

    //Buscar producto por nombre
    Optional<Producto> findByNombre(String nombre);

    List<Producto> findByCategoria(String categoria);
}



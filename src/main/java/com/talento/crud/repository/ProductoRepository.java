package com.talento.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talento.crud.model.Producto;

public interface ProductoRepository extends JpaRepository< Producto, Long> {

}

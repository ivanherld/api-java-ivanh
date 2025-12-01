package com.talento.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.talento.crud.model.Venta;

//*Long es el tipo de dato del id de la entidad Venta
public interface VentaRepository extends JpaRepository<Venta, Long> {

}

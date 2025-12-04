package com.talento.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.talento.crud.dto.VentaDTO;


@Service
public interface IVentaService {

    List<VentaDTO> traerVentas();
    VentaDTO traerVentaPorId(Long id);
    VentaDTO crearVenta(VentaDTO ventaDto);
    VentaDTO actualizarVenta(Long id, VentaDTO ventaDto);
    void eliminarVenta(Long id);
}

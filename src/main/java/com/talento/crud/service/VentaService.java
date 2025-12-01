package com.talento.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.talento.crud.dto.VentaDTO;

@Service
public class VentaService implements IVentaService {

    @Override
    public List<VentaDTO> traerVentas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'traerVentas'");
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearVenta'");
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarVenta'");
    }

    @Override
    public void eliminarVenta(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarVenta'");
    }

}

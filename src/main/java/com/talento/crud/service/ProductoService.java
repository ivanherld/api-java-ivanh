package com.talento.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.talento.crud.dto.ProductoDTO;

@Service
public class ProductoService implements IProductoService {

    @Override
    public List<ProductoDTO> traerProductos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'traerProductos'");
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearProducto'");
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarProducto'");
    }

    @Override
    public void eliminarProducto(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarProducto'");
    }

}

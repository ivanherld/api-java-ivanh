package com.talento.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.talento.crud.dto.ProductoDTO;

@Service
public interface IProductoService {

    List<ProductoDTO> traerProductos();
    ProductoDTO traerProductoPorId(Long id);
    ProductoDTO crearProducto(ProductoDTO productoDto);
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto);
    void eliminarProducto(Long id);
}

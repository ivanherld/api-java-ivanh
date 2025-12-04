package com.talento.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talento.crud.dto.ProductoDTO;
import com.talento.crud.exception.NotFoundException;
import com.talento.crud.mapper.Mapper;
import com.talento.crud.model.Producto;
import com.talento.crud.repository.ProductoRepository;

@Service
public class ProductoService implements IProductoService {

    @Autowired // Inyección de dependencia me ahorro el new
    private ProductoRepository repo;


    @Override
    public List<ProductoDTO> traerProductos() {
        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO traerProductoPorId(Long id) {
        Producto prod = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id: " + id));
        return Mapper.toDTO(prod);
    }

    @Override
    public List<ProductoDTO> traerProductosPorCategoria(String categoria) {
        return repo.findByCategoria(categoria).stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDto) {

        Producto prod = Producto.builder()
                .nombre(productoDto.getNombre())
                .descripcion(productoDto.getDescripcion())
                .categoria(productoDto.getCategoria())
                .precio(productoDto.getPrecio())
                .imagen(productoDto.getImagen())
                .cantidad(productoDto.getCantidad())
                .build();

        //*Se guarda el producto y se retorna el DTO
        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto) {
        
        //Buscamos si existe el producto
        Producto prod = repo.findById(id)   
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id: " + id));
        
        prod.setNombre(productoDto.getNombre());
        prod.setDescripcion(productoDto.getDescripcion());
        prod.setCategoria(productoDto.getCategoria());
        prod.setPrecio(productoDto.getPrecio());
        prod.setImagen(productoDto.getImagen());
        prod.setCantidad(productoDto.getCantidad());

        return Mapper.toDTO(repo.save(prod));
    }

    @Override
    public void eliminarProducto(Long id) {
        //Verificamos si existe el producto
        if (!repo.existsById(id)) {
            throw new NotFoundException("Producto no encontrado con id: " + id);
        }

        repo.deleteById(id);
    }

}

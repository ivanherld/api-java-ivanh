package com.talento.crud.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talento.crud.dto.ProductoDTO;
import com.talento.crud.service.IProductoService;

@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen (CORS)
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProducts() {
        return ResponseEntity.ok(productoService.traerProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductById(@PathVariable Long id) {
        ProductoDTO productoDto = productoService.traerProductoPorId(id);
        return ResponseEntity.ok(productoDto);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoDTO>> getProductsByCategory(@PathVariable String categoria) {
        List<ProductoDTO> productos = productoService.traerProductosPorCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> createProduct(@RequestBody ProductoDTO productoDto) {
        ProductoDTO creado = productoService.crearProducto(productoDto);
        return ResponseEntity.created(URI.create("/api/productos" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProduct(@PathVariable Long id, @RequestBody ProductoDTO productoDto) {
        ProductoDTO actualizado = productoService.actualizarProducto(id, productoDto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}

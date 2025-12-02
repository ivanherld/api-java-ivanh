package com.talento.crud.controller;

import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talento.crud.dto.VentaDTO;
import com.talento.crud.service.IVentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
 
    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> getAllSales() {
        return ResponseEntity.ok(ventaService.traerVentas());
    }

    @PostMapping
    public ResponseEntity<VentaDTO> createSale(@RequestBody VentaDTO ventaDto) {
        VentaDTO creado = ventaService.crearVenta(ventaDto);
        return ResponseEntity.created(URI.create("/api/ventas" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> updateSale(@PathVariable Long id, @RequestBody VentaDTO ventaDto) {
        VentaDTO actualizado = ventaService.actualizarVenta(id, ventaDto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}

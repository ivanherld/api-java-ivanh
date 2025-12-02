package com.talento.crud.mapper;

import java.util.stream.Collectors;

import com.talento.crud.dto.DetalleVentaDTO;
import com.talento.crud.dto.ProductoDTO;
import com.talento.crud.dto.VentaDTO;
import com.talento.crud.model.Producto;
import com.talento.crud.model.Venta;

public class Mapper {
    //*Mapeo de Producto a ProductoDTO
    //Estatico para no tener que crear una instancia de Mapper
    public static ProductoDTO toDTO(Producto p){
        if (p == null) {
            return null;
        }

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .imagen(p.getImagen())
                .cantidad(p.getCantidad())
                .build();
    }

    //*Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta v){
        if (v == null) {
            return null;
        }

       var detalle = v.getDetalle().stream().map(det ->
                DetalleVentaDTO.builder()
                        .id(det.getProd().getId())
                        .nombreProd(det.getProd().getNombre())
                        .idProd(det.getProd().getId())
                        .cantProd(det.getCantProd())
                        .precio(det.getPrecio())
                        .subtotal(det.getPrecio() * det.getCantProd())
                        .build()
        ).collect(Collectors.toList());

        var total = detalle.stream()
                .map(DetalleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum);
        
        return VentaDTO.builder()
                .id(v.getId())
                .fecha(v.getFecha())
                .estado(v.getEstado())
                .detalles(detalle)
                .total(total)
                .build();
    }

}

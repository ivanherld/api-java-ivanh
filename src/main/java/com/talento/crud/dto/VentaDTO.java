package com.talento.crud.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor 
@NoArgsConstructor
public class VentaDTO {
    //datos de la venta 
    private Long id;
    private LocalDate fecha;
    private String estado;

    //lista de detalles de venta
    private List<DetalleVentaDTO> detalles;

    //total de venta 
    private Double total;
}

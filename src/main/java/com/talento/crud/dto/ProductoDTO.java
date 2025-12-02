package com.talento.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor 
@NoArgsConstructor
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private Double precio;
    private String imagen;
    private int cantidad;
}

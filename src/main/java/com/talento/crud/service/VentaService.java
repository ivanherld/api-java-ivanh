package com.talento.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talento.crud.dto.DetalleVentaDTO;
import com.talento.crud.dto.VentaDTO;
import com.talento.crud.mapper.Mapper;
import com.talento.crud.model.DetalleVenta;
import com.talento.crud.model.Producto;
import com.talento.crud.model.Venta;
import com.talento.crud.repository.ProductoRepository;
import com.talento.crud.repository.VentaRepository;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository ventaRepo;

    @Autowired
    private ProductoRepository productoRepo;
    
    @Override
    public List<VentaDTO> traerVentas() {
        
        List<Venta> ventas = ventaRepo.findAll();
        List<VentaDTO> ventasDto = new ArrayList<>();
        VentaDTO vDto;

        for (Venta v : ventas){
            vDto = Mapper.toDTO(v);
            ventasDto.add(vDto);
        }
        return ventasDto;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {
        
        //Validaciones
        if (ventaDto == null) throw new IllegalArgumentException("El objeto ventaDto no puede ser nulo");
        if (ventaDto.getDetalles() == null || ventaDto.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La venta debe tener al menos un producto");
        }

        //Crear la venta 
        Venta vent = new Venta();
        vent.setFecha(ventaDto.getFecha());
        vent.setEstado(ventaDto.getEstado());
        vent.setTotal(ventaDto.getTotal());

        //Lista de detalles de venta
        //Acá se encuentran nuestros productos y cantidades
        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaDTO deTDO : ventaDto.getDetalles()) {
            //Buscar el producto en la base de datos
            Producto p = productoRepo.findById(deTDO.getIdProd()).orElse(null);
               if (p == null)
               {
                   throw new RuntimeException("Producto con ID " + deTDO.getIdProd() + " no encontrado");
               }
            
        //Crear detalle
        DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProd(p);
            detalleVent.setCantProd(deTDO.getCantProd());
            detalleVent.setPrecio(deTDO.getPrecio());
            detalleVent.setVenta(vent); 
            
            detalles.add(detalleVent);
        }
        //Asignar detalles a la venta
        vent.setDetalle(detalles);
            
        //Guardar la venta en la base de datos
        ventaRepo.save(vent);

        VentaDTO ventaSalida = Mapper.toDTO(vent);
        
        return ventaSalida;
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
       
        //Buscar si la venta existe para actualizarla
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) {
            throw new RuntimeException("Venta con ID " + id + " no encontrada");
        }

        if (ventaDto.getFecha() != null) {
            v.setFecha(ventaDto.getFecha());
        }
        if (ventaDto.getEstado() != null) {
            v.setEstado(ventaDto.getEstado());
        }
        if (ventaDto.getTotal() != null) {
            v.setTotal(ventaDto.getTotal());
        }

        ventaRepo.save(v);

        VentaDTO ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void eliminarVenta(Long id) {

        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) {
            throw new RuntimeException("Venta con ID " + id + " no encontrada");
        }
        ventaRepo.delete(v);
    }

}

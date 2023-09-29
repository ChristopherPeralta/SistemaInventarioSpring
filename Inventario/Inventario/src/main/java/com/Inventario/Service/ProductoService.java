package com.Inventario.Service;

import com.Inventario.Entity.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductoService {

    ResponseEntity<List<Producto>> listarProductos();

    ResponseEntity<?>GetById(Integer id);

    ResponseEntity<?>guardarProducto(Producto producto);

    ResponseEntity<?>eliminarProductoPorId(Integer idProducto);







}

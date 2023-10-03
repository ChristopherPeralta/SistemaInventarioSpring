package com.Inventario.Service;

import com.Inventario.Entity.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductoService {

    public List<Producto> listarProductos();

    public Producto GetById(Integer id);

   public void guardarProducto(Producto producto);

    public void eliminarProductoPorId(Integer idProducto);







}

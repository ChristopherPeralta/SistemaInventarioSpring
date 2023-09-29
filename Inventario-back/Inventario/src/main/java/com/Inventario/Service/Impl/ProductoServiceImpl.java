package com.Inventario.Service.Impl;

import com.Inventario.Entity.Producto;
import com.Inventario.Repository.ProductoRepository;
import com.Inventario.Service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository repository;

    @Override
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Override
    public ResponseEntity<?> GetById(Integer id) {
        Optional<Producto> producto = repository.findById(id);
        if (producto.isPresent()){
            return ResponseEntity.ok(producto.get());
        }
        return ResponseEntity.badRequest().body("Error en la búsqueda de Id producto");
    }

    @Override
    public ResponseEntity<?> guardarProducto(Producto producto) {
        HashMap<String, Object> json = new HashMap<>();

        if(producto.getPrecio() < 0 || producto.getExistencia() < 0) {
            json.put("mensaje", "Error: No se aceptan numeros negativos en Precio o Existencia");
            return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
        }
        json.put("mensaje","Producto registrado");
        repository.save(producto);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> eliminarProductoPorId(Integer idProducto) {
        return null;
    }


}

package com.Inventario.Service.Impl;

import com.Inventario.Entity.Producto;
import com.Inventario.Repository.ProductoRepository;
import com.Inventario.Service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository repository;

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<String> GetById(Integer id) {
        Optional<Producto> producto = repository.findById(id);
        return producto.map(value -> ResponseEntity.ok(String.valueOf(value))).orElseGet(() -> ResponseEntity.badRequest().body("Error en la búsqueda de Id producto"));
    }

    @Transactional
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

    @Transactional
    @Override
    public ResponseEntity<?> eliminarProductoPorId(Integer idProducto) {
        return null;
    }


}

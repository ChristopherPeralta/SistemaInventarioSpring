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

@AllArgsConstructor
@Service
public class ProductoServiceImpl implements ProductoService {

    private ProductoRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Producto> listarProductos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
        public Producto GetById(Integer id) {
        Producto producto = this.repository.findById(id).orElse(null);
        return producto;
    }

    @Transactional
    @Override
    public void guardarProducto(Producto producto) {
        this.repository.save(producto);
    }

    @Transactional
    @Override
    public void eliminarProductoPorId(Integer id) {
        this.repository.deleteById(id);
    }


}

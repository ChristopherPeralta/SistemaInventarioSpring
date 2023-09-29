package com.Inventario.Controller;

import com.Inventario.Entity.Producto;
import com.Inventario.Service.ProductoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sistema-inventario")
@CrossOrigin("*")  //puerto para angular //value = "http://localhost:4200"
@AllArgsConstructor
public class ProductoController {

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    private ProductoService productoService;

    //http://localhost:8080/
    @GetMapping ("/productos")
    public ResponseEntity<List<Producto>> obtenerProductos(){
        ResponseEntity<List<Producto>> productos = productoService.listarProductos();
        logger.info("Productos obtenidos");
        return productos;
    }

    @PostMapping("/productos")
    public ResponseEntity<?> guardarProducto(@RequestBody Producto producto){
        logger.info("Producto a agregar:" + producto);
        return productoService.guardarProducto(producto);
    }
}

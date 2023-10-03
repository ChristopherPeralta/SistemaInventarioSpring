package com.Inventario.Controller;

import com.Inventario.Entity.Producto;
import com.Inventario.Excepcion.RecursoNoEncontradoExcepcion;
import com.Inventario.Service.ProductoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sistema-inventario")
@CrossOrigin("*")  //puerto para angular //value = "http://localhost:4200"
@AllArgsConstructor
public class ProductoController {

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    private ProductoService productoService;

    //http://localhost:8080/
    @GetMapping ("/productos")
    public List<Producto> obtenerProductos(){
        List<Producto> productos = productoService.listarProductos();
        return productos;
    }

    @PostMapping("/productos")
    public ResponseEntity<?> guardarProducto(@RequestBody Producto producto){
        logger.info("Producto a agregar:" + producto);
        this.productoService.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId (@PathVariable int id){
        Producto producto = this.productoService.GetById(id);

        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id " +id);
        }
    }

    @PutMapping("productos/{id}")
    public ResponseEntity<Producto> actualizarProducto (@PathVariable int id, @RequestBody Producto productoGuardar){
        Producto producto = this.productoService.GetById(id);
        if (producto == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);

        producto.setDescripcion(productoGuardar.getDescripcion());
        producto.setPrecio(productoGuardar.getPrecio());
        producto.setExistencia(productoGuardar.getExistencia());

        this.productoService.guardarProducto(producto);
        return ResponseEntity.ok(producto);

    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable int id){
        Producto producto = productoService.GetById(id);
        if (producto == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        this.productoService.eliminarProductoPorId(producto.getId());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}


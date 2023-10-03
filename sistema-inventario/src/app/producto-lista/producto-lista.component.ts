import { Component } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { Router } from '@angular/router';
import { catchError, map, tap } from 'rxjs';

@Component({

    selector: 'app-producto-lista',
  templateUrl: './producto-lista.component.html',
})

export class ProductoListaComponent {
    productos: Producto[];

    constructor(private productoServicio: ProductoService,
      private enrutador: Router){}
    ngOnInit(){
      this.obtenerProductos();
    } 
    private obtenerProductos(){
      this.productoServicio.obtenerProductoLista().pipe(
        tap((data: any) => {
          console.log("Producto List", data);
          this.productos = data;  
        }),
        catchError((error: any) => {
          console.log("error",error);
          throw error;
      })
    )
    .subscribe();
    }

    editarProducto(id: number){
      this.enrutador.navigate(['editar-producto', id]);
    }

    eliminarProducto(id: number){
      this.productoServicio.eliminarProducto(id).pipe(
        tap((data: any) => {
          console.log("producto eliminado: ", data);
          this.obtenerProductos();
        }),
        catchError((error: any) => {
          console.log("error al eliminar producto: ", error);
          throw error;
        })
      )
      .subscribe();
    }

  }

  

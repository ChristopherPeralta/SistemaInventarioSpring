import { Component } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { Router } from '@angular/router';
import { catchError, map, tap } from 'rxjs';

@Component({
  selector: 'app-agregar-producto',
  templateUrl: './agregar-producto.component.html',
})
export class AgregarProductoComponent {
  producto: Producto = new Producto;

  constructor(private productoServicio: ProductoService,
  private enrutador: Router){}

  onSubmit(){
    this.guardarProducto();
  }
  
  guardarProducto(): void {
    this.productoServicio.guardarProducto(this.producto).pipe(
      tap((data:any) => {
        console.log("producto guardado: ", data);
        this.irListaProducto();
      }),
      catchError((error:any) => {
        console.log("error al guardar producto: ", error);
        throw error;
      })
    )
    .subscribe();
  }

  irListaProducto(){
    this.enrutador.navigate(['/productos']);
  }

}

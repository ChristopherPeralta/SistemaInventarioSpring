import { Component } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { Router } from '@angular/router';

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
  
  guardarProducto(){
    this.productoServicio.guardarProducto(this.producto).subscribe(
      {
        next: (datos) => {
          this.ListaProducto();
      },
      error: (error: any) => {console.log(error)}
      }
    );
  }

  ListaProducto(){
    this.enrutador.navigate(['/productos']);
  }

}

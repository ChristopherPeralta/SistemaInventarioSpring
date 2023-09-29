import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http'
import { Producto } from './producto';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  private urlBase = "http://localhost:8080/sistema-inventario/productos"

  constructor(private clienteHttp: HttpClient) { }

  obtenerProductoLista(): Observable<Producto[]>{
      return this.clienteHttp.get<Producto[]>(this.urlBase);
    }
}
import { Injectable } from '@angular/core';
import { Product } from '../product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private products: Product[] = [
    { id: 1, name: 'Ordi HP', price: 600000, description: 'Un PC gammer' },
    { id: 2, name: 'iphone', price: 875000, description: 'Téléphone dernière génération' },
    { id: 3, name: 'Clavier Mécanique', price: 15000, description: 'Pour les gamers' }
  ];
  private nextId = 4;

  constructor() { }

  getProducts(): Product[] {
    return this.products;
  }

  addProduct(product: Omit<Product, 'id'>): void {
    const newProduct: Product = { ...product, id: this.nextId++ };
    this.products.push(newProduct);
  }

  updateProduct(updatedProduct: Product): void {
    const index = this.products.findIndex(p => p.id === updatedProduct.id);
    if (index !== -1) {
      this.products[index] = updatedProduct;
    }
  }

  deleteProduct(id: number): void {
    this.products = this.products.filter(p => p.id !== id);
  }

  getProductById(id: number): Product | undefined {
    return this.products.find(p => p.id === id);
  }
}
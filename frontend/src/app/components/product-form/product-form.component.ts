import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.interface';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
  product: Product = {
    name: '',
    description: '',
    price: 0
  };
  editMode = false;

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.editMode = true;
      this.productService.getProduct(id).subscribe(product => {
        this.product = product;
      });
    }
  }

  setProduct(product: Product) {
    this.product = { ...product };
    this.editMode = true;
  }

  onSubmit() {
    if (this.editMode) {
      this.productService.updateProduct(this.product.id!, this.product).subscribe({
        next: () => {
          this.router.navigate(['/produtos']);
        },
        error: (error) => console.error('Erro ao atualizar produto:', error)
      });
    } else {
      this.productService.createProduct(this.product).subscribe({
        next: () => {
          this.router.navigate(['/produtos']);
        },
        error: (error) => console.error('Erro ao criar produto:', error)
      });
    }
  }
}

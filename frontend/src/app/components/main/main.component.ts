import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="jumbotron">
      <h1>Mais um CRUD</h1>
      <p>Selecione uma opção no menu acima</p>
    </div>
  `,
  styles: [`
    .jumbotron {
      padding: 2rem;
      background-color: #f8f9fa;
      border-radius: 0.3rem;
    }
  `]
})
export class MainComponent {}

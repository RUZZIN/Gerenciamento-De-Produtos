import { Routes } from '@angular/router';
import { Component } from '@angular/core';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductFormComponent } from './components/product-form/product-form.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { EmployeeFormComponent } from './components/employee-form/employee-form.component';

export const routes: Routes = [
  { path: '', redirectTo: 'funcionarios', pathMatch: 'full' },
  { path: 'home', redirectTo: 'funcionarios', pathMatch: 'full' },
  { path: 'funcionarios', component: EmployeeListComponent },
  { path: 'funcionarios/add', component: EmployeeFormComponent },
  { path: 'funcionarios/edit/:id', component: EmployeeFormComponent },
  { path: 'produtos', component: ProductListComponent },
  { path: 'produtos/novo', component: ProductFormComponent },
  { path: 'produtos/editar/:id', component: ProductFormComponent },
  { path: '**', redirectTo: '' }
];

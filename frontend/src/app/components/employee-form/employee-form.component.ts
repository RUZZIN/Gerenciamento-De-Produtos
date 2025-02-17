import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { Employee } from '../../models/employee.interface';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink
  ]
})
export class EmployeeFormComponent implements OnInit {
  employee: Employee = {
    name: '',
    company: '',
    position: '',
    salary: 0,
    age: 0,
    cpf: '',
    description: ''
  };
  isEditing = false;

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.isEditing = true;
      this.employeeService.getEmployee(id).subscribe({
        next: (employee) => {
          this.employee = employee;
        },
        error: (error) => {
          console.error('Erro ao carregar funcionário:', error);
        }
      });
    }
  }

  onSubmit(): void {
    if (this.isEditing) {
      this.employeeService.updateEmployee(this.employee).subscribe({
        next: () => {
          this.router.navigate(['/funcionarios']);
        },
        error: (error) => {
          console.error('Erro ao atualizar funcionário:', error);
        }
      });
    } else {
      this.employeeService.addEmployee(this.employee).subscribe({
        next: () => {
          this.router.navigate(['/funcionarios']);
        },
        error: (error) => {
          console.error('Erro ao adicionar funcionário:', error);
        }
      });
    }
  }
}


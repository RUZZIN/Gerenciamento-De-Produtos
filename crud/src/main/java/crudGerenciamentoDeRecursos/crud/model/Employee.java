package crudGerenciamentoDeRecursos.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Positive(message = "O salário deve ser maior que zero")
    private Double salary;

    @NotBlank(message = "Nome da empresa é obrigatório")
    private String company;

    @Positive(message = "A idade deve ser maior que zero")
    private Integer age;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "Posição é obrigatória")
    private String position;

    @NotBlank(message = "Descrição é obrigatória")
    private String description;
}

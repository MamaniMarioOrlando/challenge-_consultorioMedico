package com.codemized.challenge.consultorioMedico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 50)
    private String matricula;

    @Column
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private Boolean activo = true;

    @ManyToMany(mappedBy = "medicos")
    private Set<Consultorio> consultorios;
}

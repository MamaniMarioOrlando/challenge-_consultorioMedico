package com.codemized.challenge.consultorioMedico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 200)
    private String direccion;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 100)
    private String horarioAtencion;

    @Column(nullable = false)
    private Boolean activo = true;

    @ManyToMany
    @JoinTable(
        name = "consultorio_medico",
        joinColumns = @JoinColumn(name = "consultorio_id"),
        inverseJoinColumns = @JoinColumn(name = "medico_id")
    )
    private Set<Medico> medicos;

    @ManyToMany
    @JoinTable(
        name = "consultorio_usuario",
        joinColumns = @JoinColumn(name = "consultorio_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios;

}

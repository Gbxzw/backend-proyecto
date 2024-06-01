package com.mito_proyecto.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_matricula")
public class DetalleMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle_matricula;

    @OneToMany
    private int id_curso;
    private String aula;

    public int getId_detalle_matricula() {
        return id_detalle_matricula;
    }

    public void setId_detalle_matricula(int id_detalle_matricula) {
        this.id_detalle_matricula = id_detalle_matricula;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }
}

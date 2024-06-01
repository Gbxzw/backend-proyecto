package com.mito_proyecto.entidad;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "registrar_matricula")
public class RegistrarMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_registrar;

    Date fecha;

    @OneToMany
    private int id_estudiante;

    @OneToMany
    ArrayList<DetalleMatricula> id_detalle_matricula = new ArrayList<>();

    private Boolean estado;

    public int getId_registrar() {
        return id_registrar;
    }

    public void setId_registrar(int id_registrar) {
        this.id_registrar = id_registrar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public ArrayList<DetalleMatricula> getId_detalle_matricula() {
        return id_detalle_matricula;
    }

    public void setId_detalle_matricula(ArrayList<DetalleMatricula> id_detalle_matricula) {
        this.id_detalle_matricula = id_detalle_matricula;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}

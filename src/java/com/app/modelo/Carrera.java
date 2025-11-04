
package com.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrera")
@IdClass(CarreraPK.class)
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcarrera;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "facultad_idfacultad")
    private Facultad facultad;
    
    private String nombre;

    public Carrera() {
    }

    public Carrera(int idcarrera, String nombre, Facultad facultad) {
        this.idcarrera = idcarrera;
        this.nombre = nombre;
        this.facultad = facultad;
    }

    public int getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(int idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    @Override
    public String toString() {
        return "Carrera{" + "idcarrera=" + idcarrera + ", nombre='" + nombre + "'" + ", facultad=" + facultad + '}' ;
    }
}

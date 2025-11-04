package com.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idalumno;
    private String nombre;
    private int registro;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "carrera_idcarrera", referencedColumnName = "idcarrera"),
        @JoinColumn(name = "carrera_facultad_idfacultad", referencedColumnName = "facultad_idfacultad")
    })
    private Carrera carrera;

    public Alumno() {
    }

    public Alumno(int idalumno, String nombre, int registro, Carrera carrera) {
        this.idalumno = idalumno;
        this.nombre = nombre;
        this.registro = registro;
        this.carrera = carrera;
    }

    public int getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Alumno{" + "idalumno=" + idalumno + ", nombre='" + nombre + "'" + ", registro=" + registro + ", carrera=" + carrera + '}' ;
    }
}

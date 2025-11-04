
package com.app.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name = "facultad")
public class Facultad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idfacultad;
    private String nombre;
    
    @OneToMany(mappedBy = "facultad")
    private List<Carrera> carreraCollection;

    public Facultad() {
    }

    public Facultad(int idfacultad, String nombre) {
        this.idfacultad = idfacultad;
        this.nombre = nombre;
    }

    public List<Carrera> getCarreraCollection() {
        return carreraCollection;
    }

    public void setCarreraCollection(List<Carrera> carreraCollection) {
        this.carreraCollection = carreraCollection;
    }

    public int getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(int idfacultad) {
        this.idfacultad = idfacultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Facultad{" + "idfacultad=" + idfacultad + ", nombre='" + nombre + "'" + '}';
    }
}

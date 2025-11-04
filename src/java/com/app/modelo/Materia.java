
package com.app.modelo;

import com.app.modelo.Docente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "materia")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmateria;
    private String nombre;
    
    @ManyToMany(mappedBy = "materias")
    private java.util.List<Docente> docentes;

    public Materia() {
    }

    public int getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(int idmateria) {
        this.idmateria = idmateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public java.util.List<Docente> getDocentes() {
        return docentes;
    }

    public void setDocentes(java.util.List<Docente> docentes) {
        this.docentes = docentes;
    }

    @Override
    public String toString() {
        return "Materia{" + "idmateria=" + idmateria + ", nombre='" + nombre + "'" + '}';
    }
}

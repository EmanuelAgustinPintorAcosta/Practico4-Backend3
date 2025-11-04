
package com.app.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "examen")
public class Examen {

    @EmbeddedId
    private ExamenPK id;

    @ManyToOne
    @MapsId("alumnoId")
    @JoinColumn(name = "alumno_idalumno")
    private Alumno alumno;

    @ManyToOne
    @MapsId("materiaId")
    @JoinColumn(name = "materia_idmateria")
    private Materia materia;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    private int nota;

    public Examen() {
    }

    // Getters and setters can be regenerated based on the new structure.

    public ExamenPK getId() {
        return id;
    }

    public void setId(ExamenPK id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}

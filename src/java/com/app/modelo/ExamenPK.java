package com.app.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExamenPK implements Serializable {

    @Column(name = "alumno_idalumno")
    private int alumnoId;

    @Column(name = "materia_idmateria")
    private int materiaId;

    public ExamenPK() {
    }

    public ExamenPK(int alumnoId, int materiaId) {
        this.alumnoId = alumnoId;
        this.materiaId = materiaId;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public int getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamenPK examenPK = (ExamenPK) o;
        return alumnoId == examenPK.alumnoId && materiaId == examenPK.materiaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumnoId, materiaId);
    }
}

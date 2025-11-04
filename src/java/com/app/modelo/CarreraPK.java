package com.app.modelo;

import java.io.Serializable;
import java.util.Objects;

public class CarreraPK implements Serializable {

    private int idcarrera;
    private int facultad;

    public CarreraPK() {
    }

    public CarreraPK(int idcarrera, int facultad) {
        this.idcarrera = idcarrera;
        this.facultad = facultad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarreraPK carreraPK = (CarreraPK) o;
        return idcarrera == carreraPK.idcarrera && facultad == carreraPK.facultad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcarrera, facultad);
    }
}

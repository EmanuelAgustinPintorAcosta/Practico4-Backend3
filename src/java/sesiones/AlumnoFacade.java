/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import com.app.modelo.Alumno;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author ASUS
 */
@Stateless
public class AlumnoFacade extends AbstractFacade<Alumno> {

    @PersistenceContext(unitName = "AppAlumnoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlumnoFacade() {
        super(Alumno.class);
    }
    
    public java.util.List<com.app.modelo.Alumno> findAllWithDetails() {
        jakarta.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        jakarta.persistence.criteria.CriteriaQuery<com.app.modelo.Alumno> cq = cb.createQuery(com.app.modelo.Alumno.class);
        jakarta.persistence.criteria.Root<com.app.modelo.Alumno> a = cq.from(com.app.modelo.Alumno.class);
        jakarta.persistence.criteria.Fetch<com.app.modelo.Alumno, com.app.modelo.Carrera> c = a.fetch("carrera");
        c.fetch("facultad");
        cq.select(a).distinct(true);
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public java.util.List<com.app.modelo.Alumno> findAlumnosSinExamenesEsteAnio() {
        jakarta.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        jakarta.persistence.criteria.CriteriaQuery<com.app.modelo.Alumno> cq = cb.createQuery(com.app.modelo.Alumno.class);
        jakarta.persistence.criteria.Root<com.app.modelo.Alumno> alumno = cq.from(com.app.modelo.Alumno.class);

        // Subquery to find alumno IDs who *have* taken exams in 2025
        jakarta.persistence.criteria.Subquery<Integer> subquery = cq.subquery(Integer.class);
        jakarta.persistence.criteria.Root<com.app.modelo.Examen> examen = subquery.from(com.app.modelo.Examen.class);
        subquery.select(examen.get("id").get("alumnoId")); // Select the alumnoId from the composite PK

        // Correlate the subquery with the outer query's alumno
        jakarta.persistence.criteria.Predicate anio2025 = cb.equal(cb.function("YEAR", Integer.class, examen.get("fecha")), 2025);
        jakarta.persistence.criteria.Predicate alumnoMatch = cb.equal(examen.get("alumno"), alumno);

        subquery.where(alumnoMatch, anio2025);

        // Select alumnos whose idalumno is NOT IN the subquery results
        cq.select(alumno).where(cb.not(cb.exists(subquery)));

        return getEntityManager().createQuery(cq).getResultList();
    }
    
}

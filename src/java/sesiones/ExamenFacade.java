/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import com.app.modelo.Examen;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author ASUS
 */
@Stateless
public class ExamenFacade extends AbstractFacade<Examen> {

    @PersistenceContext(unitName = "AppAlumnoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamenFacade() {
        super(Examen.class);
    }
    
    public java.util.List<Object[]> findExamenesJulioConAlumnoNota() {
        jakarta.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        jakarta.persistence.criteria.CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        jakarta.persistence.criteria.Root<Examen> examen = cq.from(Examen.class);
        
        // Join to Alumno to get student details
        jakarta.persistence.criteria.Join<Examen, com.app.modelo.Alumno> alumno = examen.join("alumno");
        
        // Predicate for July 2025
        // Note: Assuming current year is 2025 based on context
        jakarta.persistence.criteria.Predicate p1 = cb.equal(cb.function("MONTH", Integer.class, examen.get("fecha")), 7);
        jakarta.persistence.criteria.Predicate p2 = cb.equal(cb.function("YEAR", Integer.class, examen.get("fecha")), 2025);
        
        cq.multiselect(alumno.get("nombre"), examen.get("nota"));
        cq.where(cb.and(p1, p2));
        
        return getEntityManager().createQuery(cq).getResultList();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import com.app.modelo.Carrera;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author ASUS
 */
@Stateless
public class CarreraFacade extends AbstractFacade<Carrera> {

    @PersistenceContext(unitName = "AppAlumnoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarreraFacade() {
        super(Carrera.class);
    }

    public Carrera find(int idcarrera) {
        jakarta.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        jakarta.persistence.criteria.CriteriaQuery<Carrera> cq = cb.createQuery(Carrera.class);
        jakarta.persistence.criteria.Root<Carrera> c = cq.from(Carrera.class);
        cq.select(c);
        cq.where(cb.equal(c.get("idcarrera"), idcarrera));
        return getEntityManager().createQuery(cq).getSingleResult();
    }
    
    public java.util.List<com.app.modelo.Carrera> findByFacultad(com.app.modelo.Facultad facultad) {
        jakarta.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        jakarta.persistence.criteria.CriteriaQuery<com.app.modelo.Carrera> cq = cb.createQuery(com.app.modelo.Carrera.class);
        jakarta.persistence.criteria.Root<com.app.modelo.Carrera> c = cq.from(com.app.modelo.Carrera.class);
        cq.select(c);
        cq.where(cb.equal(c.get("facultad"), facultad));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
}

package sesiones;

import com.app.modelo.Docente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author ASUS
 */
@Stateless
public class DocenteFacade extends AbstractFacade<Docente> {

    @PersistenceContext(unitName = "AppAlumnoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteFacade() {
        super(Docente.class);
    }
    
    public java.util.List<String> findDocentesConMasDeDosMaterias() {
        jakarta.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        jakarta.persistence.criteria.CriteriaQuery<String> cq = cb.createQuery(String.class);
        jakarta.persistence.criteria.Root<com.app.modelo.Docente> docente = cq.from(com.app.modelo.Docente.class);
        
        cq.select(docente.get("nombre"));
        cq.groupBy(docente.get("iddocente"), docente.get("nombre"));
        cq.having(cb.greaterThan(cb.size(docente.get("materias")), 2));
        
        return getEntityManager().createQuery(cq).getResultList();
    }
}

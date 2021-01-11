/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.generate.facade;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import seminario.entidades.Grado;
import seminario.entidades.Grado_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.Curso;

/**
 *
 * @author sergio
 */
@Stateless
public class GradoFacade extends AbstractFacade<Grado> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GradoFacade() {
        super(Grado.class);
    }

    public boolean isCursoCollectionEmpty(Grado entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Grado> grado = cq.from(Grado.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(grado, entity), cb.isNotEmpty(grado.get(Grado_.cursoCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Curso> findCursoCollection(Grado entity) {
        Grado mergedEntity = this.getMergedEntity(entity);
        Collection<Curso> cursoCollection = mergedEntity.getCursoCollection();
        cursoCollection.size();
        return cursoCollection;
    }
    
}

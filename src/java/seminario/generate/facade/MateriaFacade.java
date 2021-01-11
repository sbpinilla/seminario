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
import seminario.entidades.Materia;
import seminario.entidades.Materia_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.CursoMateriaProfesor;

/**
 *
 * @author sergio
 */
@Stateless
public class MateriaFacade extends AbstractFacade<Materia> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MateriaFacade() {
        super(Materia.class);
    }

    public boolean isCursoMateriaProfesorCollectionEmpty(Materia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Materia> materia = cq.from(Materia.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(materia, entity), cb.isNotEmpty(materia.get(Materia_.cursoMateriaProfesorCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<CursoMateriaProfesor> findCursoMateriaProfesorCollection(Materia entity) {
        Materia mergedEntity = this.getMergedEntity(entity);
        Collection<CursoMateriaProfesor> cursoMateriaProfesorCollection = mergedEntity.getCursoMateriaProfesorCollection();
        cursoMateriaProfesorCollection.size();
        return cursoMateriaProfesorCollection;
    }
    
}

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
import seminario.entidades.Profesor;
import seminario.entidades.Profesor_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.TipoDocumento;
import seminario.entidades.CursoMateriaProfesor;

/**
 *
 * @author sergio
 */
@Stateless
public class ProfesorFacade extends AbstractFacade<Profesor> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorFacade() {
        super(Profesor.class);
    }

    public boolean isIdTipoDocumentoEmpty(Profesor entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Profesor> profesor = cq.from(Profesor.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(profesor, entity), cb.isNotNull(profesor.get(Profesor_.idTipoDocumento)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoDocumento findIdTipoDocumento(Profesor entity) {
        return this.getMergedEntity(entity).getIdTipoDocumento();
    }

    public boolean isCursoMateriaProfesorCollectionEmpty(Profesor entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Profesor> profesor = cq.from(Profesor.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(profesor, entity), cb.isNotEmpty(profesor.get(Profesor_.cursoMateriaProfesorCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<CursoMateriaProfesor> findCursoMateriaProfesorCollection(Profesor entity) {
        Profesor mergedEntity = this.getMergedEntity(entity);
        Collection<CursoMateriaProfesor> cursoMateriaProfesorCollection = mergedEntity.getCursoMateriaProfesorCollection();
        cursoMateriaProfesorCollection.size();
        return cursoMateriaProfesorCollection;
    }
    
}

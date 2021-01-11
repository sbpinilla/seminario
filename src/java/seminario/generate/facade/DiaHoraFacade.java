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
import seminario.entidades.DiaHora;
import seminario.entidades.DiaHora_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.Dia;
import seminario.entidades.Hora;
import seminario.entidades.DiaHoraCursoMateriaProf;

/**
 *
 * @author sergio
 */
@Stateless
public class DiaHoraFacade extends AbstractFacade<DiaHora> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiaHoraFacade() {
        super(DiaHora.class);
    }

    public boolean isIdDiaEmpty(DiaHora entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DiaHora> diaHora = cq.from(DiaHora.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(diaHora, entity), cb.isNotNull(diaHora.get(DiaHora_.idDia)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Dia findIdDia(DiaHora entity) {
        return this.getMergedEntity(entity).getIdDia();
    }

    public boolean isIdHoraEmpty(DiaHora entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DiaHora> diaHora = cq.from(DiaHora.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(diaHora, entity), cb.isNotNull(diaHora.get(DiaHora_.idHora)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Hora findIdHora(DiaHora entity) {
        return this.getMergedEntity(entity).getIdHora();
    }

    public boolean isDiaHoraCursoMateriaProfCollectionEmpty(DiaHora entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DiaHora> diaHora = cq.from(DiaHora.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(diaHora, entity), cb.isNotEmpty(diaHora.get(DiaHora_.diaHoraCursoMateriaProfCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<DiaHoraCursoMateriaProf> findDiaHoraCursoMateriaProfCollection(DiaHora entity) {
        DiaHora mergedEntity = this.getMergedEntity(entity);
        Collection<DiaHoraCursoMateriaProf> diaHoraCursoMateriaProfCollection = mergedEntity.getDiaHoraCursoMateriaProfCollection();
        diaHoraCursoMateriaProfCollection.size();
        return diaHoraCursoMateriaProfCollection;
    }
    
}

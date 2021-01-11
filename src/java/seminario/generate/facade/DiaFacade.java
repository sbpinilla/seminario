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
import seminario.entidades.Dia;
import seminario.entidades.Dia_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.DiaHora;

/**
 *
 * @author sergio
 */
@Stateless
public class DiaFacade extends AbstractFacade<Dia> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiaFacade() {
        super(Dia.class);
    }

    public boolean isDiaHoraCollectionEmpty(Dia entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Dia> dia = cq.from(Dia.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(dia, entity), cb.isNotEmpty(dia.get(Dia_.diaHoraCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<DiaHora> findDiaHoraCollection(Dia entity) {
        Dia mergedEntity = this.getMergedEntity(entity);
        Collection<DiaHora> diaHoraCollection = mergedEntity.getDiaHoraCollection();
        diaHoraCollection.size();
        return diaHoraCollection;
    }
    
}

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
import seminario.entidades.Hora;
import seminario.entidades.Hora_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.DiaHora;

/**
 *
 * @author sergio
 */
@Stateless
public class HoraFacade extends AbstractFacade<Hora> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HoraFacade() {
        super(Hora.class);
    }

    public boolean isDiaHoraCollectionEmpty(Hora entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Hora> hora = cq.from(Hora.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(hora, entity), cb.isNotEmpty(hora.get(Hora_.diaHoraCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<DiaHora> findDiaHoraCollection(Hora entity) {
        Hora mergedEntity = this.getMergedEntity(entity);
        Collection<DiaHora> diaHoraCollection = mergedEntity.getDiaHoraCollection();
        diaHoraCollection.size();
        return diaHoraCollection;
    }
    
}

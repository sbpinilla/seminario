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
import seminario.entidades.Modulo;
import seminario.entidades.Modulo_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.ModuloRol;

/**
 *
 * @author sergio
 */
@Stateless
public class ModuloFacade extends AbstractFacade<Modulo> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModuloFacade() {
        super(Modulo.class);
    }

    public boolean isModuloRolCollectionEmpty(Modulo entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Modulo> modulo = cq.from(Modulo.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(modulo, entity), cb.isNotEmpty(modulo.get(Modulo_.moduloRolCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<ModuloRol> findModuloRolCollection(Modulo entity) {
        Modulo mergedEntity = this.getMergedEntity(entity);
        Collection<ModuloRol> moduloRolCollection = mergedEntity.getModuloRolCollection();
        moduloRolCollection.size();
        return moduloRolCollection;
    }
    
}

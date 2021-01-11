/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.generate.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import seminario.entidades.ModuloRol;
import seminario.entidades.ModuloRol_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.Modulo;
import seminario.entidades.Rol;

/**
 *
 * @author sergio
 */
@Stateless
public class ModuloRolFacade extends AbstractFacade<ModuloRol> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModuloRolFacade() {
        super(ModuloRol.class);
    }

    public boolean isIdModuloEmpty(ModuloRol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ModuloRol> moduloRol = cq.from(ModuloRol.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(moduloRol, entity), cb.isNotNull(moduloRol.get(ModuloRol_.idModulo)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Modulo findIdModulo(ModuloRol entity) {
        return this.getMergedEntity(entity).getIdModulo();
    }

    public boolean isIdRolEmpty(ModuloRol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ModuloRol> moduloRol = cq.from(ModuloRol.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(moduloRol, entity), cb.isNotNull(moduloRol.get(ModuloRol_.idRol)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Rol findIdRol(ModuloRol entity) {
        return this.getMergedEntity(entity).getIdRol();
    }
    
}

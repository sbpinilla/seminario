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
import seminario.entidades.Rol;
import seminario.entidades.Rol_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.ModuloRol;
import seminario.entidades.Usuario;

/**
 *
 * @author sergio
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    public boolean isModuloRolCollectionEmpty(Rol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Rol> rol = cq.from(Rol.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rol, entity), cb.isNotEmpty(rol.get(Rol_.moduloRolCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<ModuloRol> findModuloRolCollection(Rol entity) {
        Rol mergedEntity = this.getMergedEntity(entity);
        Collection<ModuloRol> moduloRolCollection = mergedEntity.getModuloRolCollection();
        moduloRolCollection.size();
        return moduloRolCollection;
    }

    public boolean isUsuarioCollectionEmpty(Rol entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Rol> rol = cq.from(Rol.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(rol, entity), cb.isNotEmpty(rol.get(Rol_.usuarioCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Usuario> findUsuarioCollection(Rol entity) {
        Rol mergedEntity = this.getMergedEntity(entity);
        Collection<Usuario> usuarioCollection = mergedEntity.getUsuarioCollection();
        usuarioCollection.size();
        return usuarioCollection;
    }
    
}

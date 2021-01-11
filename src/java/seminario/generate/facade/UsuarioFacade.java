/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.generate.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import seminario.entidades.Usuario;
import seminario.entidades.Usuario_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.Rol;
import seminario.entidades.TipoDocumento;

/**
 *
 * @author sergio
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public boolean isIdRolEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.idRol)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Rol findIdRol(Usuario entity) {
        return this.getMergedEntity(entity).getIdRol();
    }

    public boolean isIdTipoDocumentoEmpty(Usuario entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(usuario, entity), cb.isNotNull(usuario.get(Usuario_.idTipoDocumento)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoDocumento findIdTipoDocumento(Usuario entity) {
        return this.getMergedEntity(entity).getIdTipoDocumento();
    }

    public Usuario consultarUsuario(Usuario usuario) {

        try {
            Usuario usuarioR = (Usuario) em.createQuery("SELECT t FROM Usuario AS t WHERE t.nombreUsuario = :nu AND t.clave = :c").setParameter("nu", usuario.getNombreUsuario()).setParameter("c", usuario.getClave()).getSingleResult();
            return usuarioR;
        } catch (Exception err) {

            return null;
        }

    }

}

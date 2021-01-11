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
import seminario.entidades.TipoDocumento;
import seminario.entidades.TipoDocumento_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.Profesor;
import seminario.entidades.Estudiante;
import seminario.entidades.Usuario;
import seminario.entidades.Acudiente;

/**
 *
 * @author sergio
 */
@Stateless
public class TipoDocumentoFacade extends AbstractFacade<TipoDocumento> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoDocumentoFacade() {
        super(TipoDocumento.class);
    }

    public boolean isProfesorCollectionEmpty(TipoDocumento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoDocumento> tipoDocumento = cq.from(TipoDocumento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoDocumento, entity), cb.isNotEmpty(tipoDocumento.get(TipoDocumento_.profesorCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Profesor> findProfesorCollection(TipoDocumento entity) {
        TipoDocumento mergedEntity = this.getMergedEntity(entity);
        Collection<Profesor> profesorCollection = mergedEntity.getProfesorCollection();
        profesorCollection.size();
        return profesorCollection;
    }

    public boolean isEstudianteCollectionEmpty(TipoDocumento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoDocumento> tipoDocumento = cq.from(TipoDocumento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoDocumento, entity), cb.isNotEmpty(tipoDocumento.get(TipoDocumento_.estudianteCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Estudiante> findEstudianteCollection(TipoDocumento entity) {
        TipoDocumento mergedEntity = this.getMergedEntity(entity);
        Collection<Estudiante> estudianteCollection = mergedEntity.getEstudianteCollection();
        estudianteCollection.size();
        return estudianteCollection;
    }

    public boolean isUsuarioCollectionEmpty(TipoDocumento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoDocumento> tipoDocumento = cq.from(TipoDocumento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoDocumento, entity), cb.isNotEmpty(tipoDocumento.get(TipoDocumento_.usuarioCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Usuario> findUsuarioCollection(TipoDocumento entity) {
        TipoDocumento mergedEntity = this.getMergedEntity(entity);
        Collection<Usuario> usuarioCollection = mergedEntity.getUsuarioCollection();
        usuarioCollection.size();
        return usuarioCollection;
    }

    public boolean isAcudienteCollectionEmpty(TipoDocumento entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<TipoDocumento> tipoDocumento = cq.from(TipoDocumento.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tipoDocumento, entity), cb.isNotEmpty(tipoDocumento.get(TipoDocumento_.acudienteCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Acudiente> findAcudienteCollection(TipoDocumento entity) {
        TipoDocumento mergedEntity = this.getMergedEntity(entity);
        Collection<Acudiente> acudienteCollection = mergedEntity.getAcudienteCollection();
        acudienteCollection.size();
        return acudienteCollection;
    }
    
}

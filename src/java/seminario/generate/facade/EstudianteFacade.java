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
import seminario.entidades.Estudiante;
import seminario.entidades.Estudiante_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.EstudianteAcudiente;
import seminario.entidades.Curso;
import seminario.entidades.TipoDocumento;

/**
 *
 * @author sergio
 */
@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

    public boolean isEstudianteAcudienteCollectionEmpty(Estudiante entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Estudiante> estudiante = cq.from(Estudiante.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estudiante, entity), cb.isNotEmpty(estudiante.get(Estudiante_.estudianteAcudienteCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<EstudianteAcudiente> findEstudianteAcudienteCollection(Estudiante entity) {
        Estudiante mergedEntity = this.getMergedEntity(entity);
        Collection<EstudianteAcudiente> estudianteAcudienteCollection = mergedEntity.getEstudianteAcudienteCollection();
        estudianteAcudienteCollection.size();
        return estudianteAcudienteCollection;
    }

    public boolean isIdCursoEmpty(Estudiante entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Estudiante> estudiante = cq.from(Estudiante.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estudiante, entity), cb.isNotNull(estudiante.get(Estudiante_.idCurso)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Curso findIdCurso(Estudiante entity) {
        return this.getMergedEntity(entity).getIdCurso();
    }

    public boolean isIdTipoDocumentoEmpty(Estudiante entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Estudiante> estudiante = cq.from(Estudiante.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estudiante, entity), cb.isNotNull(estudiante.get(Estudiante_.idTipoDocumento)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoDocumento findIdTipoDocumento(Estudiante entity) {
        return this.getMergedEntity(entity).getIdTipoDocumento();
    }
    
    
     public Estudiante findDocumento(String documento ) {

        try {
            Estudiante estudiante = (Estudiante) em.createQuery("SELECT t FROM Estudiante AS t WHERE t.documento = :documento").setParameter("documento", documento).getSingleResult();
            return estudiante;
        } catch (Exception err) {

            return null;
        }

    }
    
    
}

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
import seminario.entidades.Curso;
import seminario.entidades.Curso_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.Grado;
import seminario.entidades.Estudiante;
import seminario.entidades.CursoMateriaProfesor;

/**
 *
 * @author sergio
 */
@Stateless
public class CursoFacade extends AbstractFacade<Curso> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoFacade() {
        super(Curso.class);
    }

    public boolean isIdGradoEmpty(Curso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Curso> curso = cq.from(Curso.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(curso, entity), cb.isNotNull(curso.get(Curso_.idGrado)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Grado findIdGrado(Curso entity) {
        return this.getMergedEntity(entity).getIdGrado();
    }

    public boolean isEstudianteCollectionEmpty(Curso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Curso> curso = cq.from(Curso.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(curso, entity), cb.isNotEmpty(curso.get(Curso_.estudianteCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<Estudiante> findEstudianteCollection(Curso entity) {
        Curso mergedEntity = this.getMergedEntity(entity);
        Collection<Estudiante> estudianteCollection = mergedEntity.getEstudianteCollection();
        estudianteCollection.size();
        return estudianteCollection;
    }

    public boolean isCursoMateriaProfesorCollectionEmpty(Curso entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Curso> curso = cq.from(Curso.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(curso, entity), cb.isNotEmpty(curso.get(Curso_.cursoMateriaProfesorCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<CursoMateriaProfesor> findCursoMateriaProfesorCollection(Curso entity) {
        Curso mergedEntity = this.getMergedEntity(entity);
        Collection<CursoMateriaProfesor> cursoMateriaProfesorCollection = mergedEntity.getCursoMateriaProfesorCollection();
        cursoMateriaProfesorCollection.size();
        return cursoMateriaProfesorCollection;
    }
    
}

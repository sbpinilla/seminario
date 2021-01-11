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
import seminario.entidades.CursoMateriaProfesor;
import seminario.entidades.CursoMateriaProfesor_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.Curso;
import seminario.entidades.Materia;
import seminario.entidades.Profesor;
import seminario.entidades.DiaHoraCursoMateriaProf;

/**
 *
 * @author sergio
 */
@Stateless
public class CursoMateriaProfesorFacade extends AbstractFacade<CursoMateriaProfesor> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoMateriaProfesorFacade() {
        super(CursoMateriaProfesor.class);
    }

    public boolean isIdCursoEmpty(CursoMateriaProfesor entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CursoMateriaProfesor> cursoMateriaProfesor = cq.from(CursoMateriaProfesor.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cursoMateriaProfesor, entity), cb.isNotNull(cursoMateriaProfesor.get(CursoMateriaProfesor_.idCurso)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Curso findIdCurso(CursoMateriaProfesor entity) {
        return this.getMergedEntity(entity).getIdCurso();
    }

    public boolean isIdMateriaEmpty(CursoMateriaProfesor entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CursoMateriaProfesor> cursoMateriaProfesor = cq.from(CursoMateriaProfesor.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cursoMateriaProfesor, entity), cb.isNotNull(cursoMateriaProfesor.get(CursoMateriaProfesor_.idMateria)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Materia findIdMateria(CursoMateriaProfesor entity) {
        return this.getMergedEntity(entity).getIdMateria();
    }

    public boolean isIdProfesorEmpty(CursoMateriaProfesor entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CursoMateriaProfesor> cursoMateriaProfesor = cq.from(CursoMateriaProfesor.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cursoMateriaProfesor, entity), cb.isNotNull(cursoMateriaProfesor.get(CursoMateriaProfesor_.idProfesor)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Profesor findIdProfesor(CursoMateriaProfesor entity) {
        return this.getMergedEntity(entity).getIdProfesor();
    }

    public boolean isDiaHoraCursoMateriaProfCollectionEmpty(CursoMateriaProfesor entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CursoMateriaProfesor> cursoMateriaProfesor = cq.from(CursoMateriaProfesor.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(cursoMateriaProfesor, entity), cb.isNotEmpty(cursoMateriaProfesor.get(CursoMateriaProfesor_.diaHoraCursoMateriaProfCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<DiaHoraCursoMateriaProf> findDiaHoraCursoMateriaProfCollection(CursoMateriaProfesor entity) {
        CursoMateriaProfesor mergedEntity = this.getMergedEntity(entity);
        Collection<DiaHoraCursoMateriaProf> diaHoraCursoMateriaProfCollection = mergedEntity.getDiaHoraCursoMateriaProfCollection();
        diaHoraCursoMateriaProfCollection.size();
        return diaHoraCursoMateriaProfCollection;
    }
    
}

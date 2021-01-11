/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.generate.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import seminario.entidades.DiaHoraCursoMateriaProf;
import seminario.entidades.DiaHoraCursoMateriaProf_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.CursoMateriaProfesor;
import seminario.entidades.DiaHora;

/**
 *
 * @author sergio
 */
@Stateless
public class DiaHoraCursoMateriaProfFacade extends AbstractFacade<DiaHoraCursoMateriaProf> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiaHoraCursoMateriaProfFacade() {
        super(DiaHoraCursoMateriaProf.class);
    }

    public boolean isIdCursoMateriaProfesorEmpty(DiaHoraCursoMateriaProf entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DiaHoraCursoMateriaProf> diaHoraCursoMateriaProf = cq.from(DiaHoraCursoMateriaProf.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(diaHoraCursoMateriaProf, entity), cb.isNotNull(diaHoraCursoMateriaProf.get(DiaHoraCursoMateriaProf_.idCursoMateriaProfesor)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public CursoMateriaProfesor findIdCursoMateriaProfesor(DiaHoraCursoMateriaProf entity) {
        return this.getMergedEntity(entity).getIdCursoMateriaProfesor();
    }

    public boolean isIdDiaHoraEmpty(DiaHoraCursoMateriaProf entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DiaHoraCursoMateriaProf> diaHoraCursoMateriaProf = cq.from(DiaHoraCursoMateriaProf.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(diaHoraCursoMateriaProf, entity), cb.isNotNull(diaHoraCursoMateriaProf.get(DiaHoraCursoMateriaProf_.idDiaHora)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public DiaHora findIdDiaHora(DiaHoraCursoMateriaProf entity) {
        return this.getMergedEntity(entity).getIdDiaHora();
    }
    
}

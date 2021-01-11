/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.generate.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import seminario.entidades.EstudianteAcudiente;
import seminario.entidades.EstudianteAcudiente_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.Acudiente;
import seminario.entidades.Estudiante;

/**
 *
 * @author sergio
 */
@Stateless
public class EstudianteAcudienteFacade extends AbstractFacade<EstudianteAcudiente> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteAcudienteFacade() {
        super(EstudianteAcudiente.class);
    }

    public boolean isIdAcudienteEmpty(EstudianteAcudiente entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EstudianteAcudiente> estudianteAcudiente = cq.from(EstudianteAcudiente.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estudianteAcudiente, entity), cb.isNotNull(estudianteAcudiente.get(EstudianteAcudiente_.idAcudiente)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Acudiente findIdAcudiente(EstudianteAcudiente entity) {
        return this.getMergedEntity(entity).getIdAcudiente();
    }

    public boolean isIdEstudianteEmpty(EstudianteAcudiente entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EstudianteAcudiente> estudianteAcudiente = cq.from(EstudianteAcudiente.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(estudianteAcudiente, entity), cb.isNotNull(estudianteAcudiente.get(EstudianteAcudiente_.idEstudiante)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Estudiante findIdEstudiante(EstudianteAcudiente entity) {
        return this.getMergedEntity(entity).getIdEstudiante();
    }
    
}

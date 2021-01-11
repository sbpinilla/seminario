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
import seminario.entidades.Acudiente;
import seminario.entidades.Acudiente_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seminario.entidades.EstudianteAcudiente;
import seminario.entidades.TipoDocumento;

/**
 *
 * @author sergio
 */
@Stateless
public class AcudienteFacade extends AbstractFacade<Acudiente> {

    @PersistenceContext(unitName = "SEMINARIOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcudienteFacade() {
        super(Acudiente.class);
    }

    public boolean isEstudianteAcudienteCollectionEmpty(Acudiente entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Acudiente> acudiente = cq.from(Acudiente.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(acudiente, entity), cb.isNotEmpty(acudiente.get(Acudiente_.estudianteAcudienteCollection)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Collection<EstudianteAcudiente> findEstudianteAcudienteCollection(Acudiente entity) {
        Acudiente mergedEntity = this.getMergedEntity(entity);
        Collection<EstudianteAcudiente> estudianteAcudienteCollection = mergedEntity.getEstudianteAcudienteCollection();
        estudianteAcudienteCollection.size();
        return estudianteAcudienteCollection;
    }

    public boolean isIdTipoDocumentoEmpty(Acudiente entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Acudiente> acudiente = cq.from(Acudiente.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(acudiente, entity), cb.isNotNull(acudiente.get(Acudiente_.idTipoDocumento)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public TipoDocumento findIdTipoDocumento(Acudiente entity) {
        return this.getMergedEntity(entity).getIdTipoDocumento();
    }
    
     public Acudiente findDocumento(String documento ) {

        try {
            Acudiente estudiante = (Acudiente) em.createQuery("SELECT t FROM Acudiente AS t WHERE t.documento = :documento").setParameter("documento", documento).getSingleResult();
            return estudiante;
        } catch (Exception err) {

            return null;
        }

    }
    
}

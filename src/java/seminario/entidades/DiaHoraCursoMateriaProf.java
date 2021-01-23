/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sergio
 */
@Entity
@Cacheable(false)
@Table(name = "DIA_HORA_CURSO_MATERIA_PROF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiaHoraCursoMateriaProf.findAll", query = "SELECT d FROM DiaHoraCursoMateriaProf d")
    , @NamedQuery(name = "DiaHoraCursoMateriaProf.findByIdDhCmp", query = "SELECT d FROM DiaHoraCursoMateriaProf d WHERE d.idDhCmp = :idDhCmp")})
public class DiaHoraCursoMateriaProf implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DIA_HORA_CMP")
    @SequenceGenerator(sequenceName = "SEQ_DIA_HORA_CMP", allocationSize = 1, name = "SEQ_DIA_HORA_CMP")
    @Column(name = "ID_DH_CMP")
    private BigDecimal idDhCmp;
    @JoinColumn(name = "ID_CURSO_MATERIA_PROFESOR", referencedColumnName = "ID_CURSO_MATERIA_PROFESOR")
    @ManyToOne(optional = false)
    private CursoMateriaProfesor idCursoMateriaProfesor;
    @JoinColumn(name = "ID_DIA_HORA", referencedColumnName = "ID_DIA_HORA")
    @ManyToOne(optional = false)
    private DiaHora idDiaHora;

    public DiaHoraCursoMateriaProf() {
    }

    public DiaHoraCursoMateriaProf(BigDecimal idDhCmp) {
        this.idDhCmp = idDhCmp;
    }

    public BigDecimal getIdDhCmp() {
        return idDhCmp;
    }

    public void setIdDhCmp(BigDecimal idDhCmp) {
        this.idDhCmp = idDhCmp;
    }

    public CursoMateriaProfesor getIdCursoMateriaProfesor() {
        return idCursoMateriaProfesor;
    }

    public void setIdCursoMateriaProfesor(CursoMateriaProfesor idCursoMateriaProfesor) {
        this.idCursoMateriaProfesor = idCursoMateriaProfesor;
    }

    public DiaHora getIdDiaHora() {
        return idDiaHora;
    }

    public void setIdDiaHora(DiaHora idDiaHora) {
        this.idDiaHora = idDiaHora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDhCmp != null ? idDhCmp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiaHoraCursoMateriaProf)) {
            return false;
        }
        DiaHoraCursoMateriaProf other = (DiaHoraCursoMateriaProf) object;
        if ((this.idDhCmp == null && other.idDhCmp != null) || (this.idDhCmp != null && !this.idDhCmp.equals(other.idDhCmp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seminario.entidades.DiaHoraCursoMateriaProf[ idDhCmp=" + idDhCmp + " ]";
    }
    
}

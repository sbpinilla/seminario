/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sergio
 */
@Entity
@Cacheable(false)
@Table(name = "CURSO_MATERIA_PROFESOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CursoMateriaProfesor.findAll", query = "SELECT c FROM CursoMateriaProfesor c")
    , @NamedQuery(name = "CursoMateriaProfesor.findByIdCursoMateriaProfesor", query = "SELECT c FROM CursoMateriaProfesor c WHERE c.idCursoMateriaProfesor = :idCursoMateriaProfesor")})
public class CursoMateriaProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CURSO_MATERIA_PROFESOR")
    @SequenceGenerator(sequenceName = "SEQ_CURSO_MATERIA_PROFESOR", allocationSize = 1, name = "SEQ_CURSO_MATERIA_PROFESOR")
    @Column(name = "ID_CURSO_MATERIA_PROFESOR")
    private BigDecimal idCursoMateriaProfesor;
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO")
    @ManyToOne(optional = false)
    private Curso idCurso;
    @JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID_MATERIA")
    @ManyToOne(optional = false)
    private Materia idMateria;
    @JoinColumn(name = "ID_PROFESOR", referencedColumnName = "ID_PROFESOR")
    @ManyToOne(optional = false)
    private Profesor idProfesor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCursoMateriaProfesor")
    private Collection<DiaHoraCursoMateriaProf> diaHoraCursoMateriaProfCollection;

    public CursoMateriaProfesor() {
    }

    public CursoMateriaProfesor(BigDecimal idCursoMateriaProfesor) {
        this.idCursoMateriaProfesor = idCursoMateriaProfesor;
    }

    public BigDecimal getIdCursoMateriaProfesor() {
        return idCursoMateriaProfesor;
    }

    public void setIdCursoMateriaProfesor(BigDecimal idCursoMateriaProfesor) {
        this.idCursoMateriaProfesor = idCursoMateriaProfesor;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public Profesor getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesor idProfesor) {
        this.idProfesor = idProfesor;
    }

    @XmlTransient
    public Collection<DiaHoraCursoMateriaProf> getDiaHoraCursoMateriaProfCollection() {
        return diaHoraCursoMateriaProfCollection;
    }

    public void setDiaHoraCursoMateriaProfCollection(Collection<DiaHoraCursoMateriaProf> diaHoraCursoMateriaProfCollection) {
        this.diaHoraCursoMateriaProfCollection = diaHoraCursoMateriaProfCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursoMateriaProfesor != null ? idCursoMateriaProfesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoMateriaProfesor)) {
            return false;
        }
        CursoMateriaProfesor other = (CursoMateriaProfesor) object;
        if ((this.idCursoMateriaProfesor == null && other.idCursoMateriaProfesor != null) || (this.idCursoMateriaProfesor != null && !this.idCursoMateriaProfesor.equals(other.idCursoMateriaProfesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seminario.entidades.CursoMateriaProfesor[ idCursoMateriaProfesor=" + idCursoMateriaProfesor + " ]";
    }
    
}

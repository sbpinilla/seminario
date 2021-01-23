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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sergio
 */
@Entity
@Cacheable(false)
@Table(name = "ESTUDIANTE_ACUDIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstudianteAcudiente.findAll", query = "SELECT e FROM EstudianteAcudiente e")
    , @NamedQuery(name = "EstudianteAcudiente.findByIdEstudianteAcudiente", query = "SELECT e FROM EstudianteAcudiente e WHERE e.idEstudianteAcudiente = :idEstudianteAcudiente")
    , @NamedQuery(name = "EstudianteAcudiente.findByParentesco", query = "SELECT e FROM EstudianteAcudiente e WHERE e.parentesco = :parentesco")})
public class EstudianteAcudiente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ESTUDIANTE_ACUDIENTE")
    @SequenceGenerator(sequenceName = "SEQ_ESTUDIANTE_ACUDIENTE", allocationSize = 1, name = "SEQ_ESTUDIANTE_ACUDIENTE")
    @Column(name = "ID_ESTUDIANTE_ACUDIENTE")
    private BigDecimal idEstudianteAcudiente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PARENTESCO")
    private String parentesco;
    @JoinColumn(name = "ID_ACUDIENTE", referencedColumnName = "ID_ACUDIENTE")
    @ManyToOne(optional = false)
    private Acudiente idAcudiente;
    @JoinColumn(name = "ID_ESTUDIANTE", referencedColumnName = "ID_ESTUDIANTE")
    @ManyToOne(optional = false)
    private Estudiante idEstudiante;

    public EstudianteAcudiente() {
    }

    public EstudianteAcudiente(BigDecimal idEstudianteAcudiente) {
        this.idEstudianteAcudiente = idEstudianteAcudiente;
    }

    public EstudianteAcudiente(BigDecimal idEstudianteAcudiente, String parentesco) {
        this.idEstudianteAcudiente = idEstudianteAcudiente;
        this.parentesco = parentesco;
    }

    public BigDecimal getIdEstudianteAcudiente() {
        return idEstudianteAcudiente;
    }

    public void setIdEstudianteAcudiente(BigDecimal idEstudianteAcudiente) {
        this.idEstudianteAcudiente = idEstudianteAcudiente;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Acudiente getIdAcudiente() {
        return idAcudiente;
    }

    public void setIdAcudiente(Acudiente idAcudiente) {
        this.idAcudiente = idAcudiente;
    }

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudianteAcudiente != null ? idEstudianteAcudiente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudianteAcudiente)) {
            return false;
        }
        EstudianteAcudiente other = (EstudianteAcudiente) object;
        if ((this.idEstudianteAcudiente == null && other.idEstudianteAcudiente != null) || (this.idEstudianteAcudiente != null && !this.idEstudianteAcudiente.equals(other.idEstudianteAcudiente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seminario.entidades.EstudianteAcudiente[ idEstudianteAcudiente=" + idEstudianteAcudiente + " ]";
    }
    
}

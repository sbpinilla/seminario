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
@Table(name = "DIA_HORA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiaHora.findAll", query = "SELECT d FROM DiaHora d")
    , @NamedQuery(name = "DiaHora.findByIdDiaHora", query = "SELECT d FROM DiaHora d WHERE d.idDiaHora = :idDiaHora")})
public class DiaHora implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DIA_HORA")
    @SequenceGenerator(sequenceName = "SEQ_DIA_HORA", allocationSize = 1, name = "SEQ_DIA_HORA")
    @Column(name = "ID_DIA_HORA")
    private BigDecimal idDiaHora;
    @JoinColumn(name = "ID_DIA", referencedColumnName = "ID_DIA")
    @ManyToOne(optional = false)
    private Dia idDia;
    @JoinColumn(name = "ID_HORA", referencedColumnName = "ID_HORA")
    @ManyToOne(optional = false)
    private Hora idHora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDiaHora")
    private Collection<DiaHoraCursoMateriaProf> diaHoraCursoMateriaProfCollection;

    public DiaHora() {
    }

    public DiaHora(BigDecimal idDiaHora) {
        this.idDiaHora = idDiaHora;
    }

    public BigDecimal getIdDiaHora() {
        return idDiaHora;
    }

    public void setIdDiaHora(BigDecimal idDiaHora) {
        this.idDiaHora = idDiaHora;
    }

    public Dia getIdDia() {
        return idDia;
    }

    public void setIdDia(Dia idDia) {
        this.idDia = idDia;
    }

    public Hora getIdHora() {
        return idHora;
    }

    public void setIdHora(Hora idHora) {
        this.idHora = idHora;
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
        hash += (idDiaHora != null ? idDiaHora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiaHora)) {
            return false;
        }
        DiaHora other = (DiaHora) object;
        if ((this.idDiaHora == null && other.idDiaHora != null) || (this.idDiaHora != null && !this.idDiaHora.equals(other.idDiaHora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seminario.entidades.DiaHora[ idDiaHora=" + idDiaHora + " ]";
    }
    
}

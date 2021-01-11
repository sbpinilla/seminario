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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sergio
 */
@Entity
@Table(name = "DIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dia.findAll", query = "SELECT d FROM Dia d")
    , @NamedQuery(name = "Dia.findByIdDia", query = "SELECT d FROM Dia d WHERE d.idDia = :idDia")
    , @NamedQuery(name = "Dia.findByNombre", query = "SELECT d FROM Dia d WHERE d.nombre = :nombre")})
public class Dia implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DIA")
    @SequenceGenerator(sequenceName = "SEQ_DIA", allocationSize = 1, name = "SEQ_DIA")
    @Column(name = "ID_DIA")
    private BigDecimal idDia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDia")
    private Collection<DiaHora> diaHoraCollection;

    public Dia() {
    }

    public Dia(BigDecimal idDia) {
        this.idDia = idDia;
    }

    public Dia(BigDecimal idDia, String nombre) {
        this.idDia = idDia;
        this.nombre = nombre;
    }

    public BigDecimal getIdDia() {
        return idDia;
    }

    public void setIdDia(BigDecimal idDia) {
        this.idDia = idDia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<DiaHora> getDiaHoraCollection() {
        return diaHoraCollection;
    }

    public void setDiaHoraCollection(Collection<DiaHora> diaHoraCollection) {
        this.diaHoraCollection = diaHoraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDia != null ? idDia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dia)) {
            return false;
        }
        Dia other = (Dia) object;
        if ((this.idDia == null && other.idDia != null) || (this.idDia != null && !this.idDia.equals(other.idDia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seminario.entidades.Dia[ idDia=" + idDia + " ]";
    }
    
}

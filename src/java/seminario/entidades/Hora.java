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
@Table(name = "HORA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hora.findAll", query = "SELECT h FROM Hora h")
    , @NamedQuery(name = "Hora.findByIdHora", query = "SELECT h FROM Hora h WHERE h.idHora = :idHora")
    , @NamedQuery(name = "Hora.findByNombre", query = "SELECT h FROM Hora h WHERE h.nombre = :nombre")})
public class Hora implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HORA")
    @SequenceGenerator(sequenceName = "SEQ_HORA", allocationSize = 1, name = "SEQ_HORA")
    @Column(name = "ID_HORA")
    private BigDecimal idHora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHora")
    private Collection<DiaHora> diaHoraCollection;

    public Hora() {
    }

    public Hora(BigDecimal idHora) {
        this.idHora = idHora;
    }

    public Hora(BigDecimal idHora, String nombre) {
        this.idHora = idHora;
        this.nombre = nombre;
    }

    public BigDecimal getIdHora() {
        return idHora;
    }

    public void setIdHora(BigDecimal idHora) {
        this.idHora = idHora;
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
        hash += (idHora != null ? idHora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hora)) {
            return false;
        }
        Hora other = (Hora) object;
        if ((this.idHora == null && other.idHora != null) || (this.idHora != null && !this.idHora.equals(other.idHora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seminario.entidades.Hora[ idHora=" + idHora + " ]";
    }
    
}

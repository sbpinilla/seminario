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
@Cacheable(false)
@Table(name = "MODULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modulo.findAll", query = "SELECT m FROM Modulo m")
    , @NamedQuery(name = "Modulo.findByIdModulo", query = "SELECT m FROM Modulo m WHERE m.idModulo = :idModulo")
    , @NamedQuery(name = "Modulo.findByNombre", query = "SELECT m FROM Modulo m WHERE m.nombre = :nombre")})
public class Modulo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MODULO")
    @SequenceGenerator(sequenceName = "SEQ_MODULO", allocationSize = 1, name = "SEQ_MODULO")
    @Column(name = "ID_MODULO")
    private BigDecimal idModulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModulo")
    private Collection<ModuloRol> moduloRolCollection;

    public Modulo() {
    }

    public Modulo(BigDecimal idModulo) {
        this.idModulo = idModulo;
    }

    public Modulo(BigDecimal idModulo, String nombre) {
        this.idModulo = idModulo;
        this.nombre = nombre;
    }

    public BigDecimal getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(BigDecimal idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<ModuloRol> getModuloRolCollection() {
        return moduloRolCollection;
    }

    public void setModuloRolCollection(Collection<ModuloRol> moduloRolCollection) {
        this.moduloRolCollection = moduloRolCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModulo != null ? idModulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modulo)) {
            return false;
        }
        Modulo other = (Modulo) object;
        if ((this.idModulo == null && other.idModulo != null) || (this.idModulo != null && !this.idModulo.equals(other.idModulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seminario.entidades.Modulo[ idModulo=" + idModulo + " ]";
    }
    
}

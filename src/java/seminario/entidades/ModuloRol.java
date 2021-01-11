/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
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
@Table(name = "MODULO_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModuloRol.findAll", query = "SELECT m FROM ModuloRol m")
    , @NamedQuery(name = "ModuloRol.findByIdModuloRol", query = "SELECT m FROM ModuloRol m WHERE m.idModuloRol = :idModuloRol")})
public class ModuloRol implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MODULO_ROL")
    @SequenceGenerator(sequenceName = "SEQ_MODULO_ROL", allocationSize = 1, name = "SEQ_MODULO_ROL")
    @Column(name = "ID_MODULO_ROL")
    private BigDecimal idModuloRol;
    @JoinColumn(name = "ID_MODULO", referencedColumnName = "ID_MODULO")
    @ManyToOne(optional = false)
    private Modulo idModulo;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne(optional = false)
    private Rol idRol;

    public ModuloRol() {
    }

    public ModuloRol(BigDecimal idModuloRol) {
        this.idModuloRol = idModuloRol;
    }

    public BigDecimal getIdModuloRol() {
        return idModuloRol;
    }

    public void setIdModuloRol(BigDecimal idModuloRol) {
        this.idModuloRol = idModuloRol;
    }

    public Modulo getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Modulo idModulo) {
        this.idModulo = idModulo;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModuloRol != null ? idModuloRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModuloRol)) {
            return false;
        }
        ModuloRol other = (ModuloRol) object;
        if ((this.idModuloRol == null && other.idModuloRol != null) || (this.idModuloRol != null && !this.idModuloRol.equals(other.idModuloRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seminario.entidades.ModuloRol[ idModuloRol=" + idModuloRol + " ]";
    }
    
}

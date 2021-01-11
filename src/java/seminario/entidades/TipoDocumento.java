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
@Table(name = "TIPO_DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t")
    , @NamedQuery(name = "TipoDocumento.findByIdTipoDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.idTipoDocumento = :idTipoDocumento")
    , @NamedQuery(name = "TipoDocumento.findByNombre", query = "SELECT t FROM TipoDocumento t WHERE t.nombre = :nombre")})
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPO_DOCUMENTO")
    @SequenceGenerator(sequenceName = "SEQ_TIPO_DOCUMENTO", allocationSize = 1, name = "SEQ_TIPO_DOCUMENTO")
    @Column(name = "ID_TIPO_DOCUMENTO")
    private BigDecimal idTipoDocumento;
    @Size(max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private Collection<Profesor> profesorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private Collection<Estudiante> estudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private Collection<Usuario> usuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private Collection<Acudiente> acudienteCollection;

    public TipoDocumento() {
    }

    public TipoDocumento(BigDecimal idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public BigDecimal getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(BigDecimal idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Profesor> getProfesorCollection() {
        return profesorCollection;
    }

    public void setProfesorCollection(Collection<Profesor> profesorCollection) {
        this.profesorCollection = profesorCollection;
    }

    @XmlTransient
    public Collection<Estudiante> getEstudianteCollection() {
        return estudianteCollection;
    }

    public void setEstudianteCollection(Collection<Estudiante> estudianteCollection) {
        this.estudianteCollection = estudianteCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<Acudiente> getAcudienteCollection() {
        return acudienteCollection;
    }

    public void setAcudienteCollection(Collection<Acudiente> acudienteCollection) {
        this.acudienteCollection = acudienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDocumento != null ? idTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.idTipoDocumento == null && other.idTipoDocumento != null) || (this.idTipoDocumento != null && !this.idTipoDocumento.equals(other.idTipoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seminario.entidades.TipoDocumento[ idTipoDocumento=" + idTipoDocumento + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sergio
 */
@Entity
@Table(name = "ACUDIENTE")
@XmlRootElement
@Cacheable(false)
@NamedQueries({
    @NamedQuery(name = "Acudiente.findAll", query = "SELECT a FROM Acudiente a")
    , @NamedQuery(name = "Acudiente.findByIdAcudiente", query = "SELECT a FROM Acudiente a WHERE a.idAcudiente = :idAcudiente")
    , @NamedQuery(name = "Acudiente.findByNombres", query = "SELECT a FROM Acudiente a WHERE a.nombres = :nombres")
    , @NamedQuery(name = "Acudiente.findByApellidos", query = "SELECT a FROM Acudiente a WHERE a.apellidos = :apellidos")
    , @NamedQuery(name = "Acudiente.findByFechaNacimiento", query = "SELECT a FROM Acudiente a WHERE a.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Acudiente.findByTelefono", query = "SELECT a FROM Acudiente a WHERE a.telefono = :telefono")
    , @NamedQuery(name = "Acudiente.findByDireccion", query = "SELECT a FROM Acudiente a WHERE a.direccion = :direccion")
    , @NamedQuery(name = "Acudiente.findByDocumento", query = "SELECT a FROM Acudiente a WHERE a.documento = :documento")})
public class Acudiente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACUDIENTE")
    @SequenceGenerator(sequenceName = "SEQ_ACUDIENTE", allocationSize = 1, name = "SEQ_ACUDIENTE")
    @Column(name = "ID_ACUDIENTE")
    private BigDecimal idAcudiente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DIRECCION")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DOCUMENTO")
    private String documento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAcudiente")
    private Collection<EstudianteAcudiente> estudianteAcudienteCollection;
    @JoinColumn(name = "ID_TIPO_DOCUMENTO", referencedColumnName = "ID_TIPO_DOCUMENTO")
    @ManyToOne(optional = false)
    private TipoDocumento idTipoDocumento;

    public Acudiente() {
    }

    public Acudiente(BigDecimal idAcudiente) {
        this.idAcudiente = idAcudiente;
    }

    public Acudiente(BigDecimal idAcudiente, String nombres, String apellidos, Date fechaNacimiento, String telefono, String direccion, String documento) {
        this.idAcudiente = idAcudiente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.documento = documento;
    }

    public BigDecimal getIdAcudiente() {
        return idAcudiente;
    }

    public void setIdAcudiente(BigDecimal idAcudiente) {
        this.idAcudiente = idAcudiente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @XmlTransient
    public Collection<EstudianteAcudiente> getEstudianteAcudienteCollection() {
        return estudianteAcudienteCollection;
    }

    public void setEstudianteAcudienteCollection(Collection<EstudianteAcudiente> estudianteAcudienteCollection) {
        this.estudianteAcudienteCollection = estudianteAcudienteCollection;
    }

    public TipoDocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(TipoDocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcudiente != null ? idAcudiente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acudiente)) {
            return false;
        }
        Acudiente other = (Acudiente) object;
        if ((this.idAcudiente == null && other.idAcudiente != null) || (this.idAcudiente != null && !this.idAcudiente.equals(other.idAcudiente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seminario.entidades.Acudiente[ idAcudiente=" + idAcudiente + " ]";
    }
    
}

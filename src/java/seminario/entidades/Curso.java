/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sergio
 */
@Entity
@Cacheable(false)
@Table(name = "CURSO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c")
    , @NamedQuery(name = "Curso.findByIdCurso", query = "SELECT c FROM Curso c WHERE c.idCurso = :idCurso")
    , @NamedQuery(name = "Curso.findByNombre", query = "SELECT c FROM Curso c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Curso.findByAnio", query = "SELECT c FROM Curso c WHERE c.anio = :anio")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CURSO")
    @SequenceGenerator(sequenceName = "SEQ_CURSO", allocationSize = 1, name = "SEQ_CURSO")
   
    @Column(name = "ID_CURSO")
    private BigDecimal idCurso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANIO")
    private BigInteger anio;
    @JoinColumn(name = "ID_GRADO", referencedColumnName = "ID_GRADO")
    @ManyToOne(optional = false)
    private Grado idGrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<Estudiante> estudianteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurso")
    private Collection<CursoMateriaProfesor> cursoMateriaProfesorCollection;

    public Curso() {
    }

    public Curso(BigDecimal idCurso) {
        this.idCurso = idCurso;
    }

    public Curso(BigDecimal idCurso, String nombre, BigInteger anio) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.anio = anio;
    }

    public BigDecimal getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(BigDecimal idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getAnio() {
        return anio;
    }

    public void setAnio(BigInteger anio) {
        this.anio = anio;
    }

    public Grado getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Grado idGrado) {
        this.idGrado = idGrado;
    }

    @XmlTransient
    public Collection<Estudiante> getEstudianteCollection() {
        return estudianteCollection;
    }

    public void setEstudianteCollection(Collection<Estudiante> estudianteCollection) {
        this.estudianteCollection = estudianteCollection;
    }

    @XmlTransient
    public Collection<CursoMateriaProfesor> getCursoMateriaProfesorCollection() {
        return cursoMateriaProfesorCollection;
    }

    public void setCursoMateriaProfesorCollection(Collection<CursoMateriaProfesor> cursoMateriaProfesorCollection) {
        this.cursoMateriaProfesorCollection = cursoMateriaProfesorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seminario.entidades.Curso[ idCurso=" + idCurso + " ]";
    }
    
}

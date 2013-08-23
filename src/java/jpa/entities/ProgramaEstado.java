/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Smart
 */
@Entity
@Table(name = "programa_estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgramaEstado.findAll", query = "SELECT p FROM ProgramaEstado p"),
    @NamedQuery(name = "ProgramaEstado.findByIdProgramaEstado", query = "SELECT p FROM ProgramaEstado p WHERE p.idProgramaEstado = :idProgramaEstado"),
    @NamedQuery(name = "ProgramaEstado.findByDescrProgramaEstado", query = "SELECT p FROM ProgramaEstado p WHERE p.descrProgramaEstado = :descrProgramaEstado")})
public class ProgramaEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_programa_estado")
    private Short idProgramaEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descr_programa_estado")
    private String descrProgramaEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProgramaEstado")
    private List<MatrizCaracterizacion> matrizCaracterizacionList;

    public ProgramaEstado() {
    }

    public ProgramaEstado(Short idProgramaEstado) {
        this.idProgramaEstado = idProgramaEstado;
    }

    public ProgramaEstado(Short idProgramaEstado, String descrProgramaEstado) {
        this.idProgramaEstado = idProgramaEstado;
        this.descrProgramaEstado = descrProgramaEstado;
    }

    public Short getIdProgramaEstado() {
        return idProgramaEstado;
    }

    public void setIdProgramaEstado(Short idProgramaEstado) {
        this.idProgramaEstado = idProgramaEstado;
    }

    public String getDescrProgramaEstado() {
        return descrProgramaEstado;
    }

    public void setDescrProgramaEstado(String descrProgramaEstado) {
        this.descrProgramaEstado = descrProgramaEstado;
    }

    @XmlTransient
    public List<MatrizCaracterizacion> getMatrizCaracterizacionList() {
        return matrizCaracterizacionList;
    }

    public void setMatrizCaracterizacionList(List<MatrizCaracterizacion> matrizCaracterizacionList) {
        this.matrizCaracterizacionList = matrizCaracterizacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProgramaEstado != null ? idProgramaEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramaEstado)) {
            return false;
        }
        ProgramaEstado other = (ProgramaEstado) object;
        if ((this.idProgramaEstado == null && other.idProgramaEstado != null) || (this.idProgramaEstado != null && !this.idProgramaEstado.equals(other.idProgramaEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.ProgramaEstado[ idProgramaEstado=" + idProgramaEstado + " ]";
    }
    
}

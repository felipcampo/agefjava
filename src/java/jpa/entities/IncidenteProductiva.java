/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Smart
 */
@Entity
@Table(name = "incidente_productiva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncidenteProductiva.findAll", query = "SELECT i FROM IncidenteProductiva i"),
    @NamedQuery(name = "IncidenteProductiva.findByIdIncidenteProductiva", query = "SELECT i FROM IncidenteProductiva i WHERE i.idIncidenteProductiva = :idIncidenteProductiva"),
    @NamedQuery(name = "IncidenteProductiva.findByDetalleIncidente", query = "SELECT i FROM IncidenteProductiva i WHERE i.detalleIncidente = :detalleIncidente"),
    @NamedQuery(name = "IncidenteProductiva.findByFecIncio", query = "SELECT i FROM IncidenteProductiva i WHERE i.fecIncio = :fecIncio"),
    @NamedQuery(name = "IncidenteProductiva.findByTipoIncio", query = "SELECT i FROM IncidenteProductiva i WHERE i.tipoIncio = :tipoIncio")})
public class IncidenteProductiva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "id_incidente_productiva")
    private String idIncidenteProductiva;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "detalle_incidente")
    private String detalleIncidente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_incio")
    @Temporal(TemporalType.DATE)
    private Date fecIncio;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "solucion_inc")
    private String solucionInc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tipo_incio")
    private String tipoIncio;
    @JoinColumn(name = "id_visita_etapa_productiva", referencedColumnName = "id_visita_etapa_productiva")
    @ManyToOne(optional = false)
    private VisitaEtapaProductiva idVisitaEtapaProductiva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIncidenteProductiva")
    private List<SeguimientoProductiva> seguimientoProductivaList;

    public IncidenteProductiva() {
    }

    public IncidenteProductiva(String idIncidenteProductiva) {
        this.idIncidenteProductiva = idIncidenteProductiva;
    }

    public IncidenteProductiva(String idIncidenteProductiva, String detalleIncidente, Date fecIncio, String solucionInc, String tipoIncio) {
        this.idIncidenteProductiva = idIncidenteProductiva;
        this.detalleIncidente = detalleIncidente;
        this.fecIncio = fecIncio;
        this.solucionInc = solucionInc;
        this.tipoIncio = tipoIncio;
    }

    public String getIdIncidenteProductiva() {
        return idIncidenteProductiva;
    }

    public void setIdIncidenteProductiva(String idIncidenteProductiva) {
        this.idIncidenteProductiva = idIncidenteProductiva;
    }

    public String getDetalleIncidente() {
        return detalleIncidente;
    }

    public void setDetalleIncidente(String detalleIncidente) {
        this.detalleIncidente = detalleIncidente;
    }

    public Date getFecIncio() {
        return fecIncio;
    }

    public void setFecIncio(Date fecIncio) {
        this.fecIncio = fecIncio;
    }

    public String getSolucionInc() {
        return solucionInc;
    }

    public void setSolucionInc(String solucionInc) {
        this.solucionInc = solucionInc;
    }

    public String getTipoIncio() {
        return tipoIncio;
    }

    public void setTipoIncio(String tipoIncio) {
        this.tipoIncio = tipoIncio;
    }

    public VisitaEtapaProductiva getIdVisitaEtapaProductiva() {
        return idVisitaEtapaProductiva;
    }

    public void setIdVisitaEtapaProductiva(VisitaEtapaProductiva idVisitaEtapaProductiva) {
        this.idVisitaEtapaProductiva = idVisitaEtapaProductiva;
    }

    @XmlTransient
    public List<SeguimientoProductiva> getSeguimientoProductivaList() {
        return seguimientoProductivaList;
    }

    public void setSeguimientoProductivaList(List<SeguimientoProductiva> seguimientoProductivaList) {
        this.seguimientoProductivaList = seguimientoProductivaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIncidenteProductiva != null ? idIncidenteProductiva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncidenteProductiva)) {
            return false;
        }
        IncidenteProductiva other = (IncidenteProductiva) object;
        if ((this.idIncidenteProductiva == null && other.idIncidenteProductiva != null) || (this.idIncidenteProductiva != null && !this.idIncidenteProductiva.equals(other.idIncidenteProductiva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.IncidenteProductiva[ idIncidenteProductiva=" + idIncidenteProductiva + " ]";
    }
    
}

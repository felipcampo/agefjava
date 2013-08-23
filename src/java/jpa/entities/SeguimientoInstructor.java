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
@Table(name = "seguimiento_instructor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeguimientoInstructor.findAll", query = "SELECT s FROM SeguimientoInstructor s"),
    @NamedQuery(name = "SeguimientoInstructor.findByIdSeguimientoInstructor", query = "SELECT s FROM SeguimientoInstructor s WHERE s.idSeguimientoInstructor = :idSeguimientoInstructor"),
    @NamedQuery(name = "SeguimientoInstructor.findByArea", query = "SELECT s FROM SeguimientoInstructor s WHERE s.area = :area"),
    @NamedQuery(name = "SeguimientoInstructor.findByFecSegIns", query = "SELECT s FROM SeguimientoInstructor s WHERE s.fecSegIns = :fecSegIns"),
    @NamedQuery(name = "SeguimientoInstructor.findByHorFin", query = "SELECT s FROM SeguimientoInstructor s WHERE s.horFin = :horFin"),
    @NamedQuery(name = "SeguimientoInstructor.findByHorIni", query = "SELECT s FROM SeguimientoInstructor s WHERE s.horIni = :horIni"),
    @NamedQuery(name = "SeguimientoInstructor.findByNomIns", query = "SELECT s FROM SeguimientoInstructor s WHERE s.nomIns = :nomIns")})
public class SeguimientoInstructor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "id_seguimiento_instructor")
    private String idSeguimientoInstructor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "area")
    private String area;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_seg_ins")
    @Temporal(TemporalType.DATE)
    private Date fecSegIns;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hor_fin")
    @Temporal(TemporalType.TIME)
    private Date horFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hor_ini")
    @Temporal(TemporalType.TIME)
    private Date horIni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nom_ins")
    private String nomIns;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSeguimientoInstructor")
    private List<CriterioSeguimientoInstructor> criterioSeguimientoInstructorList;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @JoinColumn(name = "id_titulo_criterio", referencedColumnName = "id_titulo_criterio")
    @ManyToOne(optional = false)
    private TituloCriterio idTituloCriterio;
    @JoinColumn(name = "id_tipo_juicio", referencedColumnName = "id_tipo_juicio")
    @ManyToOne(optional = false)
    private TipoJuicio idTipoJuicio;
    @JoinColumn(name = "id_grado_juicio", referencedColumnName = "id_grado_juicio")
    @ManyToOne(optional = false)
    private GradoJuicio idGradoJuicio;
    @JoinColumn(name = "id_ficha_caracterizacion", referencedColumnName = "id_ficha_caracterizacion")
    @ManyToOne(optional = false)
    private FichaCaracterizacion idFichaCaracterizacion;
    @JoinColumn(name = "id_estado_juicio", referencedColumnName = "id_estado_juicio")
    @ManyToOne(optional = false)
    private EstadoJuicio idEstadoJuicio;

    public SeguimientoInstructor() {
    }

    public SeguimientoInstructor(String idSeguimientoInstructor) {
        this.idSeguimientoInstructor = idSeguimientoInstructor;
    }

    public SeguimientoInstructor(String idSeguimientoInstructor, String area, Date fecSegIns, Date horFin, Date horIni, String nomIns) {
        this.idSeguimientoInstructor = idSeguimientoInstructor;
        this.area = area;
        this.fecSegIns = fecSegIns;
        this.horFin = horFin;
        this.horIni = horIni;
        this.nomIns = nomIns;
    }

    public String getIdSeguimientoInstructor() {
        return idSeguimientoInstructor;
    }

    public void setIdSeguimientoInstructor(String idSeguimientoInstructor) {
        this.idSeguimientoInstructor = idSeguimientoInstructor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getFecSegIns() {
        return fecSegIns;
    }

    public void setFecSegIns(Date fecSegIns) {
        this.fecSegIns = fecSegIns;
    }

    public Date getHorFin() {
        return horFin;
    }

    public void setHorFin(Date horFin) {
        this.horFin = horFin;
    }

    public Date getHorIni() {
        return horIni;
    }

    public void setHorIni(Date horIni) {
        this.horIni = horIni;
    }

    public String getNomIns() {
        return nomIns;
    }

    public void setNomIns(String nomIns) {
        this.nomIns = nomIns;
    }

    @XmlTransient
    public List<CriterioSeguimientoInstructor> getCriterioSeguimientoInstructorList() {
        return criterioSeguimientoInstructorList;
    }

    public void setCriterioSeguimientoInstructorList(List<CriterioSeguimientoInstructor> criterioSeguimientoInstructorList) {
        this.criterioSeguimientoInstructorList = criterioSeguimientoInstructorList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TituloCriterio getIdTituloCriterio() {
        return idTituloCriterio;
    }

    public void setIdTituloCriterio(TituloCriterio idTituloCriterio) {
        this.idTituloCriterio = idTituloCriterio;
    }

    public TipoJuicio getIdTipoJuicio() {
        return idTipoJuicio;
    }

    public void setIdTipoJuicio(TipoJuicio idTipoJuicio) {
        this.idTipoJuicio = idTipoJuicio;
    }

    public GradoJuicio getIdGradoJuicio() {
        return idGradoJuicio;
    }

    public void setIdGradoJuicio(GradoJuicio idGradoJuicio) {
        this.idGradoJuicio = idGradoJuicio;
    }

    public FichaCaracterizacion getIdFichaCaracterizacion() {
        return idFichaCaracterizacion;
    }

    public void setIdFichaCaracterizacion(FichaCaracterizacion idFichaCaracterizacion) {
        this.idFichaCaracterizacion = idFichaCaracterizacion;
    }

    public EstadoJuicio getIdEstadoJuicio() {
        return idEstadoJuicio;
    }

    public void setIdEstadoJuicio(EstadoJuicio idEstadoJuicio) {
        this.idEstadoJuicio = idEstadoJuicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeguimientoInstructor != null ? idSeguimientoInstructor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoInstructor)) {
            return false;
        }
        SeguimientoInstructor other = (SeguimientoInstructor) object;
        if ((this.idSeguimientoInstructor == null && other.idSeguimientoInstructor != null) || (this.idSeguimientoInstructor != null && !this.idSeguimientoInstructor.equals(other.idSeguimientoInstructor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.SeguimientoInstructor[ idSeguimientoInstructor=" + idSeguimientoInstructor + " ]";
    }
    
}

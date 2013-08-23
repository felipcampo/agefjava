/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "plan_mejoramiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanMejoramiento.findAll", query = "SELECT p FROM PlanMejoramiento p"),
    @NamedQuery(name = "PlanMejoramiento.findByIdPlanMejoramiento", query = "SELECT p FROM PlanMejoramiento p WHERE p.idPlanMejoramiento = :idPlanMejoramiento"),
    @NamedQuery(name = "PlanMejoramiento.findByFecFin", query = "SELECT p FROM PlanMejoramiento p WHERE p.fecFin = :fecFin"),
    @NamedQuery(name = "PlanMejoramiento.findByFecIni", query = "SELECT p FROM PlanMejoramiento p WHERE p.fecIni = :fecIni"),
    @NamedQuery(name = "PlanMejoramiento.findByFecRevision", query = "SELECT p FROM PlanMejoramiento p WHERE p.fecRevision = :fecRevision"),
    @NamedQuery(name = "PlanMejoramiento.findByTipoPlanMejoramiento", query = "SELECT p FROM PlanMejoramiento p WHERE p.tipoPlanMejoramiento = :tipoPlanMejoramiento"),
    @NamedQuery(name = "PlanMejoramiento.findByJuicioEvaluacion", query = "SELECT p FROM PlanMejoramiento p WHERE p.juicioEvaluacion = :juicioEvaluacion"),
    @NamedQuery(name = "PlanMejoramiento.findByEtapa", query = "SELECT p FROM PlanMejoramiento p WHERE p.etapa = :etapa"),
    @NamedQuery(name = "PlanMejoramiento.findByLugar", query = "SELECT p FROM PlanMejoramiento p WHERE p.lugar = :lugar"),
    @NamedQuery(name = "PlanMejoramiento.findByLugar2", query = "SELECT p FROM PlanMejoramiento p WHERE p.lugar2 = :lugar2"),
    @NamedQuery(name = "PlanMejoramiento.findByPertinencia", query = "SELECT p FROM PlanMejoramiento p WHERE p.pertinencia = :pertinencia"),
    @NamedQuery(name = "PlanMejoramiento.findByVigencia", query = "SELECT p FROM PlanMejoramiento p WHERE p.vigencia = :vigencia"),
    @NamedQuery(name = "PlanMejoramiento.findByAutenticidad", query = "SELECT p FROM PlanMejoramiento p WHERE p.autenticidad = :autenticidad"),
    @NamedQuery(name = "PlanMejoramiento.findByCalidad", query = "SELECT p FROM PlanMejoramiento p WHERE p.calidad = :calidad"),
    @NamedQuery(name = "PlanMejoramiento.findByAprendizaje", query = "SELECT p FROM PlanMejoramiento p WHERE p.aprendizaje = :aprendizaje"),
    @NamedQuery(name = "PlanMejoramiento.findByTotal", query = "SELECT p FROM PlanMejoramiento p WHERE p.total = :total"),
    @NamedQuery(name = "PlanMejoramiento.findByNombreJefe", query = "SELECT p FROM PlanMejoramiento p WHERE p.nombreJefe = :nombreJefe"),
    @NamedQuery(name = "PlanMejoramiento.findByFechaConcertacion", query = "SELECT p FROM PlanMejoramiento p WHERE p.fechaConcertacion = :fechaConcertacion")})
public class PlanMejoramiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_plan_mejoramiento")
    private Integer idPlanMejoramiento;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "actividades_desarrollar")
    private String actividadesDesarrollar;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "evidencia_cam")
    private String evidenciaCam;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_fin")
    @Temporal(TemporalType.DATE)
    private Date fecFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_ini")
    @Temporal(TemporalType.DATE)
    private Date fecIni;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_revision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRevision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_plan_mejoramiento")
    private boolean tipoPlanMejoramiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "juicio_evaluacion")
    private boolean juicioEvaluacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "etapa")
    private boolean etapa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lugar")
    private boolean lugar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lugar2")
    private boolean lugar2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pertinencia")
    private boolean pertinencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vigencia")
    private boolean vigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autenticidad")
    private boolean autenticidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calidad")
    private boolean calidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aprendizaje")
    private boolean aprendizaje;
    @Size(max = 10)
    @Column(name = "total")
    private String total;
    @Lob
    @Size(max = 65535)
    @Column(name = "compromiso")
    private String compromiso;
    @Size(max = 45)
    @Column(name = "nombre_jefe")
    private String nombreJefe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_concertacion")
    @Temporal(TemporalType.DATE)
    private Date fechaConcertacion;
    @ManyToMany(mappedBy = "planMejoramientoList")
    private List<ResultadoAprendizaje> resultadoAprendizajeList;
    @JoinColumn(name = "id_comite", referencedColumnName = "id_comite")
    @ManyToOne(optional = false)
    private Comite idComite;
    @JoinColumn(name = "id_ficha_caracterizacion", referencedColumnName = "id_ficha_caracterizacion")
    @ManyToOne(optional = false)
    private FichaCaracterizacion idFichaCaracterizacion;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne(optional = false)
    private Empresa idEmpresa;
    @JoinColumn(name = "id_ciudad_concertacion", referencedColumnName = "id_ciudad")
    @ManyToOne(optional = false)
    private Ciudad idCiudadConcertacion;
    @JoinColumn(name = "id_falta", referencedColumnName = "id_falta")
    @ManyToOne(optional = false)
    private Falta idFalta;
    @JoinColumn(name = "id_estado_plan", referencedColumnName = "id_estado_plan")
    @ManyToOne(optional = false)
    private EstadoPlan idEstadoPlan;
    @JoinColumn(name = "id_actividad_proyecto", referencedColumnName = "id_actividad_proyecto")
    @ManyToOne(optional = false)
    private ActividadProyecto idActividadProyecto;
    @JoinColumn(name = "id_resultado_aprendizaje", referencedColumnName = "id_resultado_aprendizaje")
    @ManyToOne(optional = false)
    private ResultadoAprendizaje idResultadoAprendizaje;
    @JoinColumn(name = "id_fase_proyecto", referencedColumnName = "id_fase_proyecto")
    @ManyToOne(optional = false)
    private FaseProyecto idFaseProyecto;
    @JoinColumn(name = "id_usuario2", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario2;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public PlanMejoramiento() {
    }

    public PlanMejoramiento(Integer idPlanMejoramiento) {
        this.idPlanMejoramiento = idPlanMejoramiento;
    }

    public PlanMejoramiento(Integer idPlanMejoramiento, String actividadesDesarrollar, String observaciones, String evidenciaCam, Date fecFin, Date fecIni, Date fecRevision, boolean tipoPlanMejoramiento, boolean juicioEvaluacion, boolean etapa, boolean lugar, boolean lugar2, boolean pertinencia, boolean vigencia, boolean autenticidad, boolean calidad, boolean aprendizaje, Date fechaConcertacion) {
        this.idPlanMejoramiento = idPlanMejoramiento;
        this.actividadesDesarrollar = actividadesDesarrollar;
        this.observaciones = observaciones;
        this.evidenciaCam = evidenciaCam;
        this.fecFin = fecFin;
        this.fecIni = fecIni;
        this.fecRevision = fecRevision;
        this.tipoPlanMejoramiento = tipoPlanMejoramiento;
        this.juicioEvaluacion = juicioEvaluacion;
        this.etapa = etapa;
        this.lugar = lugar;
        this.lugar2 = lugar2;
        this.pertinencia = pertinencia;
        this.vigencia = vigencia;
        this.autenticidad = autenticidad;
        this.calidad = calidad;
        this.aprendizaje = aprendizaje;
        this.fechaConcertacion = fechaConcertacion;
    }

    public Integer getIdPlanMejoramiento() {
        return idPlanMejoramiento;
    }

    public void setIdPlanMejoramiento(Integer idPlanMejoramiento) {
        this.idPlanMejoramiento = idPlanMejoramiento;
    }

    public String getActividadesDesarrollar() {
        return actividadesDesarrollar;
    }

    public void setActividadesDesarrollar(String actividadesDesarrollar) {
        this.actividadesDesarrollar = actividadesDesarrollar;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEvidenciaCam() {
        return evidenciaCam;
    }

    public void setEvidenciaCam(String evidenciaCam) {
        this.evidenciaCam = evidenciaCam;
    }

    public Date getFecFin() {
        return fecFin;
    }

    public void setFecFin(Date fecFin) {
        this.fecFin = fecFin;
    }

    public Date getFecIni() {
        return fecIni;
    }

    public void setFecIni(Date fecIni) {
        this.fecIni = fecIni;
    }

    public Date getFecRevision() {
        return fecRevision;
    }

    public void setFecRevision(Date fecRevision) {
        this.fecRevision = fecRevision;
    }

    public boolean getTipoPlanMejoramiento() {
        return tipoPlanMejoramiento;
    }

    public void setTipoPlanMejoramiento(boolean tipoPlanMejoramiento) {
        this.tipoPlanMejoramiento = tipoPlanMejoramiento;
    }

    public boolean getJuicioEvaluacion() {
        return juicioEvaluacion;
    }

    public void setJuicioEvaluacion(boolean juicioEvaluacion) {
        this.juicioEvaluacion = juicioEvaluacion;
    }

    public boolean getEtapa() {
        return etapa;
    }

    public void setEtapa(boolean etapa) {
        this.etapa = etapa;
    }

    public boolean getLugar() {
        return lugar;
    }

    public void setLugar(boolean lugar) {
        this.lugar = lugar;
    }

    public boolean getLugar2() {
        return lugar2;
    }

    public void setLugar2(boolean lugar2) {
        this.lugar2 = lugar2;
    }

    public boolean getPertinencia() {
        return pertinencia;
    }

    public void setPertinencia(boolean pertinencia) {
        this.pertinencia = pertinencia;
    }

    public boolean getVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }

    public boolean getAutenticidad() {
        return autenticidad;
    }

    public void setAutenticidad(boolean autenticidad) {
        this.autenticidad = autenticidad;
    }

    public boolean getCalidad() {
        return calidad;
    }

    public void setCalidad(boolean calidad) {
        this.calidad = calidad;
    }

    public boolean getAprendizaje() {
        return aprendizaje;
    }

    public void setAprendizaje(boolean aprendizaje) {
        this.aprendizaje = aprendizaje;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCompromiso() {
        return compromiso;
    }

    public void setCompromiso(String compromiso) {
        this.compromiso = compromiso;
    }

    public String getNombreJefe() {
        return nombreJefe;
    }

    public void setNombreJefe(String nombreJefe) {
        this.nombreJefe = nombreJefe;
    }

    public Date getFechaConcertacion() {
        return fechaConcertacion;
    }

    public void setFechaConcertacion(Date fechaConcertacion) {
        this.fechaConcertacion = fechaConcertacion;
    }

    @XmlTransient
    public List<ResultadoAprendizaje> getResultadoAprendizajeList() {
        return resultadoAprendizajeList;
    }

    public void setResultadoAprendizajeList(List<ResultadoAprendizaje> resultadoAprendizajeList) {
        this.resultadoAprendizajeList = resultadoAprendizajeList;
    }

    public Comite getIdComite() {
        return idComite;
    }

    public void setIdComite(Comite idComite) {
        this.idComite = idComite;
    }

    public FichaCaracterizacion getIdFichaCaracterizacion() {
        return idFichaCaracterizacion;
    }

    public void setIdFichaCaracterizacion(FichaCaracterizacion idFichaCaracterizacion) {
        this.idFichaCaracterizacion = idFichaCaracterizacion;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Ciudad getIdCiudadConcertacion() {
        return idCiudadConcertacion;
    }

    public void setIdCiudadConcertacion(Ciudad idCiudadConcertacion) {
        this.idCiudadConcertacion = idCiudadConcertacion;
    }

    public Falta getIdFalta() {
        return idFalta;
    }

    public void setIdFalta(Falta idFalta) {
        this.idFalta = idFalta;
    }

    public EstadoPlan getIdEstadoPlan() {
        return idEstadoPlan;
    }

    public void setIdEstadoPlan(EstadoPlan idEstadoPlan) {
        this.idEstadoPlan = idEstadoPlan;
    }

    public ActividadProyecto getIdActividadProyecto() {
        return idActividadProyecto;
    }

    public void setIdActividadProyecto(ActividadProyecto idActividadProyecto) {
        this.idActividadProyecto = idActividadProyecto;
    }

    public ResultadoAprendizaje getIdResultadoAprendizaje() {
        return idResultadoAprendizaje;
    }

    public void setIdResultadoAprendizaje(ResultadoAprendizaje idResultadoAprendizaje) {
        this.idResultadoAprendizaje = idResultadoAprendizaje;
    }

    public FaseProyecto getIdFaseProyecto() {
        return idFaseProyecto;
    }

    public void setIdFaseProyecto(FaseProyecto idFaseProyecto) {
        this.idFaseProyecto = idFaseProyecto;
    }

    public Usuario getIdUsuario2() {
        return idUsuario2;
    }

    public void setIdUsuario2(Usuario idUsuario2) {
        this.idUsuario2 = idUsuario2;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanMejoramiento != null ? idPlanMejoramiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanMejoramiento)) {
            return false;
        }
        PlanMejoramiento other = (PlanMejoramiento) object;
        if ((this.idPlanMejoramiento == null && other.idPlanMejoramiento != null) || (this.idPlanMejoramiento != null && !this.idPlanMejoramiento.equals(other.idPlanMejoramiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.PlanMejoramiento[ idPlanMejoramiento=" + idPlanMejoramiento + " ]";
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.EvaluacionCriterioSeguimientoProyecto;

/**
 *
 * @author Smart
 */
@Stateless
public class EvaluacionCriterioSeguimientoProyectoFacade extends AbstractFacade<EvaluacionCriterioSeguimientoProyecto> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluacionCriterioSeguimientoProyectoFacade() {
        super(EvaluacionCriterioSeguimientoProyecto.class);
    }
    
}

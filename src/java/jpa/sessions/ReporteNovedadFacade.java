/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.ReporteNovedad;

/**
 *
 * @author Smart
 */
@Stateless
public class ReporteNovedadFacade extends AbstractFacade<ReporteNovedad> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReporteNovedadFacade() {
        super(ReporteNovedad.class);
    }
    
}

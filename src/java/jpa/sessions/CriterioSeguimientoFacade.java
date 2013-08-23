/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.CriterioSeguimiento;

/**
 *
 * @author Smart
 */
@Stateless
public class CriterioSeguimientoFacade extends AbstractFacade<CriterioSeguimiento> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CriterioSeguimientoFacade() {
        super(CriterioSeguimiento.class);
    }
    
}

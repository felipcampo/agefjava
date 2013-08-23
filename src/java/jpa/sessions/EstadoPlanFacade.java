/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.EstadoPlan;

/**
 *
 * @author Smart
 */
@Stateless
public class EstadoPlanFacade extends AbstractFacade<EstadoPlan> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoPlanFacade() {
        super(EstadoPlan.class);
    }
    
}

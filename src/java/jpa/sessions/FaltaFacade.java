/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Falta;

/**
 *
 * @author Smart
 */
@Stateless
public class FaltaFacade extends AbstractFacade<Falta> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FaltaFacade() {
        super(Falta.class);
    }
    
}

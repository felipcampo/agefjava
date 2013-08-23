/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.SituacionMilitar;

/**
 *
 * @author Smart
 */
@Stateless
public class SituacionMilitarFacade extends AbstractFacade<SituacionMilitar> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SituacionMilitarFacade() {
        super(SituacionMilitar.class);
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.GuiaAprendizaje;

/**
 *
 * @author Smart
 */
@Stateless
public class GuiaAprendizajeFacade extends AbstractFacade<GuiaAprendizaje> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GuiaAprendizajeFacade() {
        super(GuiaAprendizaje.class);
    }
    
}

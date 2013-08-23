/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.ActividadProyecto;

/**
 *
 * @author Smart
 */
@Stateless
public class ActividadProyectoFacade extends AbstractFacade<ActividadProyecto> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActividadProyectoFacade() {
        super(ActividadProyecto.class);
    }
    
}

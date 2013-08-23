/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.FaseProyecto;

/**
 *
 * @author Smart
 */
@Stateless
public class FaseProyectoFacade extends AbstractFacade<FaseProyecto> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FaseProyectoFacade() {
        super(FaseProyecto.class);
    }
    
}

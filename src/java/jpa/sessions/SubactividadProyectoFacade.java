/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.SubactividadProyecto;

/**
 *
 * @author Smart
 */
@Stateless
public class SubactividadProyectoFacade extends AbstractFacade<SubactividadProyecto> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubactividadProyectoFacade() {
        super(SubactividadProyecto.class);
    }
    
}

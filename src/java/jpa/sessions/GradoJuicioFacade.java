/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.GradoJuicio;

/**
 *
 * @author Smart
 */
@Stateless
public class GradoJuicioFacade extends AbstractFacade<GradoJuicio> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GradoJuicioFacade() {
        super(GradoJuicio.class);
    }
    
}

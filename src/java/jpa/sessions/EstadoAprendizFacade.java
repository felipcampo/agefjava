/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.EstadoAprendiz;

/**
 *
 * @author Smart
 */
@Stateless
public class EstadoAprendizFacade extends AbstractFacade<EstadoAprendiz> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoAprendizFacade() {
        super(EstadoAprendiz.class);
    }
    
}

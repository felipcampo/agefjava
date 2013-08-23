/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.JefeHogar;

/**
 *
 * @author Smart
 */
@Stateless
public class JefeHogarFacade extends AbstractFacade<JefeHogar> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JefeHogarFacade() {
        super(JefeHogar.class);
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.CasoBienestar;

/**
 *
 * @author Smart
 */
@Stateless
public class CasoBienestarFacade extends AbstractFacade<CasoBienestar> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CasoBienestarFacade() {
        super(CasoBienestar.class);
    }
    
}

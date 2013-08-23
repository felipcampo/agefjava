/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.ParentescoContacto;

/**
 *
 * @author Smart
 */
@Stateless
public class ParentescoContactoFacade extends AbstractFacade<ParentescoContacto> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParentescoContactoFacade() {
        super(ParentescoContacto.class);
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.VerificacionAmbienteTitulado;

/**
 *
 * @author Smart
 */
@Stateless
public class VerificacionAmbienteTituladoFacade extends AbstractFacade<VerificacionAmbienteTitulado> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VerificacionAmbienteTituladoFacade() {
        super(VerificacionAmbienteTitulado.class);
    }
    
}

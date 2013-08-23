/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.TipoEvidenciaAprendizaje;

/**
 *
 * @author Smart
 */
@Stateless
public class TipoEvidenciaAprendizajeFacade extends AbstractFacade<TipoEvidenciaAprendizaje> {
    @PersistenceContext(unitName = "agefjavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEvidenciaAprendizajeFacade() {
        super(TipoEvidenciaAprendizaje.class);
    }
    
}

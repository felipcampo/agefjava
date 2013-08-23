package jsf;

import jpa.entities.VerificacionAmbienteTitulado;
import jsf.util.JsfUtil;
import jpa.sessions.VerificacionAmbienteTituladoFacade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@Named("verificacionAmbienteTituladoController")
@SessionScoped
public class VerificacionAmbienteTituladoController implements Serializable {

    private VerificacionAmbienteTitulado current;
    private LazyDataModel<VerificacionAmbienteTitulado> lazyModel = null;
    @EJB
    private jpa.sessions.VerificacionAmbienteTituladoFacade ejbFacade;

    public VerificacionAmbienteTituladoController() {
    }

    public VerificacionAmbienteTitulado getSelected() {
        if (current == null) {
            current = new VerificacionAmbienteTitulado();
        }
        return current;
    }

    public void setSelected(VerificacionAmbienteTitulado entity) {
        current = entity;
    }

    private VerificacionAmbienteTituladoFacade getFacade() {
        return ejbFacade;
    }

    public LazyDataModel<VerificacionAmbienteTitulado> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new LazyDataModel<VerificacionAmbienteTitulado>() {
                @Override
                public List<VerificacionAmbienteTitulado> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

                    lazyModel.setRowCount(getFacade().count(filters));
                    List<VerificacionAmbienteTitulado> result = getFacade().getResultList(first, pageSize, sortField, sortOrder, filters);
                    if (result.isEmpty()) {
                        lazyModel.setRowCount(getFacade().count(new HashMap<String, String>()));
                    }
                    return result;
                }

                @Override
                public Object getRowKey(VerificacionAmbienteTitulado entity) {
                    return entity.getIdVerificacionAmbienteTitulado();
                }

                @Override
                public VerificacionAmbienteTitulado getRowData(String rowKey) {
                    try {
                        return getFacade().find(rowKey);
                    } catch (Exception e) {
                        return null;
                    }
                }
            };
            lazyModel.setRowCount(getFacade().count(new HashMap<String, String>()));
        }
        return lazyModel;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (VerificacionAmbienteTitulado) getLazyModel().getRowData();
        return "View";
    }

    public String prepareCreate() {
        current = new VerificacionAmbienteTitulado();
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("VerificacionAmbienteTituladoCreated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (VerificacionAmbienteTitulado) getLazyModel().getRowData();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("VerificacionAmbienteTituladoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        performDestroy();
        recreateModel();
        return "List";
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("VerificacionAmbienteTituladoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void recreateModel() {
        lazyModel = null;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = VerificacionAmbienteTitulado.class)
    public static class VerificacionAmbienteTituladoControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VerificacionAmbienteTituladoController controller = (VerificacionAmbienteTituladoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "verificacionAmbienteTituladoController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof VerificacionAmbienteTitulado) {
                VerificacionAmbienteTitulado o = (VerificacionAmbienteTitulado) object;
                return getStringKey(o.getIdVerificacionAmbienteTitulado());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + VerificacionAmbienteTitulado.class.getName());
            }
        }
    }
}

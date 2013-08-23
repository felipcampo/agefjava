package jsf;

import jpa.entities.FichaCaracterizacion;
import jsf.util.JsfUtil;
import jpa.sessions.FichaCaracterizacionFacade;

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

@Named("fichaCaracterizacionController")
@SessionScoped
public class FichaCaracterizacionController implements Serializable {

    private FichaCaracterizacion current;
    private LazyDataModel<FichaCaracterizacion> lazyModel = null;
    @EJB
    private jpa.sessions.FichaCaracterizacionFacade ejbFacade;

    public FichaCaracterizacionController() {
    }

    public FichaCaracterizacion getSelected() {
        if (current == null) {
            current = new FichaCaracterizacion();
        }
        return current;
    }

    public void setSelected(FichaCaracterizacion entity) {
        current = entity;
    }

    private FichaCaracterizacionFacade getFacade() {
        return ejbFacade;
    }

    public LazyDataModel<FichaCaracterizacion> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new LazyDataModel<FichaCaracterizacion>() {
                @Override
                public List<FichaCaracterizacion> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

                    lazyModel.setRowCount(getFacade().count(filters));
                    List<FichaCaracterizacion> result = getFacade().getResultList(first, pageSize, sortField, sortOrder, filters);
                    if (result.isEmpty()) {
                        lazyModel.setRowCount(getFacade().count(new HashMap<String, String>()));
                    }
                    return result;
                }

                @Override
                public Object getRowKey(FichaCaracterizacion entity) {
                    return entity.getIdFichaCaracterizacion();
                }

                @Override
                public FichaCaracterizacion getRowData(String rowKey) {
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
        current = (FichaCaracterizacion) getLazyModel().getRowData();
        return "View";
    }

    public String prepareCreate() {
        current = new FichaCaracterizacion();
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("FichaCaracterizacionCreated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (FichaCaracterizacion) getLazyModel().getRowData();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("FichaCaracterizacionUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("FichaCaracterizacionDeleted"));
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

    @FacesConverter(forClass = FichaCaracterizacion.class)
    public static class FichaCaracterizacionControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FichaCaracterizacionController controller = (FichaCaracterizacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fichaCaracterizacionController");
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
            if (object instanceof FichaCaracterizacion) {
                FichaCaracterizacion o = (FichaCaracterizacion) object;
                return getStringKey(o.getIdFichaCaracterizacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FichaCaracterizacion.class.getName());
            }
        }
    }
}

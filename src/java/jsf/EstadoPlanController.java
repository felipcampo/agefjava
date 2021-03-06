package jsf;

import jpa.entities.EstadoPlan;
import jsf.util.JsfUtil;
import jpa.sessions.EstadoPlanFacade;

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

@Named("estadoPlanController")
@SessionScoped
public class EstadoPlanController implements Serializable {

    private EstadoPlan current;
    private LazyDataModel<EstadoPlan> lazyModel = null;
    @EJB
    private jpa.sessions.EstadoPlanFacade ejbFacade;

    public EstadoPlanController() {
    }

    public EstadoPlan getSelected() {
        if (current == null) {
            current = new EstadoPlan();
        }
        return current;
    }

    public void setSelected(EstadoPlan entity) {
        current = entity;
    }

    private EstadoPlanFacade getFacade() {
        return ejbFacade;
    }

    public LazyDataModel<EstadoPlan> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new LazyDataModel<EstadoPlan>() {
                @Override
                public List<EstadoPlan> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

                    lazyModel.setRowCount(getFacade().count(filters));
                    List<EstadoPlan> result = getFacade().getResultList(first, pageSize, sortField, sortOrder, filters);
                    if (result.isEmpty()) {
                        lazyModel.setRowCount(getFacade().count(new HashMap<String, String>()));
                    }
                    return result;
                }

                @Override
                public Object getRowKey(EstadoPlan entity) {
                    return entity.getIdEstadoPlan();
                }

                @Override
                public EstadoPlan getRowData(String rowKey) {
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
        current = (EstadoPlan) getLazyModel().getRowData();
        return "View";
    }

    public String prepareCreate() {
        current = new EstadoPlan();
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EstadoPlanCreated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (EstadoPlan) getLazyModel().getRowData();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EstadoPlanUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EstadoPlanDeleted"));
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

    @FacesConverter(forClass = EstadoPlan.class)
    public static class EstadoPlanControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoPlanController controller = (EstadoPlanController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadoPlanController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Short getKey(String value) {
            java.lang.Short key;
            key = Short.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Short value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof EstadoPlan) {
                EstadoPlan o = (EstadoPlan) object;
                return getStringKey(o.getIdEstadoPlan());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstadoPlan.class.getName());
            }
        }
    }
}

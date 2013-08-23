package jsf;

import jpa.entities.ServicioVivienda;
import jsf.util.JsfUtil;
import jpa.sessions.ServicioViviendaFacade;

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

@Named("servicioViviendaController")
@SessionScoped
public class ServicioViviendaController implements Serializable {

    private ServicioVivienda current;
    private LazyDataModel<ServicioVivienda> lazyModel = null;
    @EJB
    private jpa.sessions.ServicioViviendaFacade ejbFacade;

    public ServicioViviendaController() {
    }

    public ServicioVivienda getSelected() {
        if (current == null) {
            current = new ServicioVivienda();
        }
        return current;
    }

    public void setSelected(ServicioVivienda entity) {
        current = entity;
    }

    private ServicioViviendaFacade getFacade() {
        return ejbFacade;
    }

    public LazyDataModel<ServicioVivienda> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new LazyDataModel<ServicioVivienda>() {
                @Override
                public List<ServicioVivienda> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

                    lazyModel.setRowCount(getFacade().count(filters));
                    List<ServicioVivienda> result = getFacade().getResultList(first, pageSize, sortField, sortOrder, filters);
                    if (result.isEmpty()) {
                        lazyModel.setRowCount(getFacade().count(new HashMap<String, String>()));
                    }
                    return result;
                }

                @Override
                public Object getRowKey(ServicioVivienda entity) {
                    return entity.getIdServicioVivienda();
                }

                @Override
                public ServicioVivienda getRowData(String rowKey) {
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
        current = (ServicioVivienda) getLazyModel().getRowData();
        return "View";
    }

    public String prepareCreate() {
        current = new ServicioVivienda();
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("ServicioViviendaCreated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (ServicioVivienda) getLazyModel().getRowData();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("ServicioViviendaUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("ServicioViviendaDeleted"));
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

    @FacesConverter(forClass = ServicioVivienda.class)
    public static class ServicioViviendaControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ServicioViviendaController controller = (ServicioViviendaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "servicioViviendaController");
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
            if (object instanceof ServicioVivienda) {
                ServicioVivienda o = (ServicioVivienda) object;
                return getStringKey(o.getIdServicioVivienda());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ServicioVivienda.class.getName());
            }
        }
    }
}

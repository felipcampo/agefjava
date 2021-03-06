package jsf;

import jpa.entities.SubactividadProyecto;
import jsf.util.JsfUtil;
import jpa.sessions.SubactividadProyectoFacade;

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

@Named("subactividadProyectoController")
@SessionScoped
public class SubactividadProyectoController implements Serializable {

    private SubactividadProyecto current;
    private LazyDataModel<SubactividadProyecto> lazyModel = null;
    @EJB
    private jpa.sessions.SubactividadProyectoFacade ejbFacade;

    public SubactividadProyectoController() {
    }

    public SubactividadProyecto getSelected() {
        if (current == null) {
            current = new SubactividadProyecto();
        }
        return current;
    }

    public void setSelected(SubactividadProyecto entity) {
        current = entity;
    }

    private SubactividadProyectoFacade getFacade() {
        return ejbFacade;
    }

    public LazyDataModel<SubactividadProyecto> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new LazyDataModel<SubactividadProyecto>() {
                @Override
                public List<SubactividadProyecto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

                    lazyModel.setRowCount(getFacade().count(filters));
                    List<SubactividadProyecto> result = getFacade().getResultList(first, pageSize, sortField, sortOrder, filters);
                    if (result.isEmpty()) {
                        lazyModel.setRowCount(getFacade().count(new HashMap<String, String>()));
                    }
                    return result;
                }

                @Override
                public Object getRowKey(SubactividadProyecto entity) {
                    return entity.getIdSubactividadProyecto();
                }

                @Override
                public SubactividadProyecto getRowData(String rowKey) {
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
        current = (SubactividadProyecto) getLazyModel().getRowData();
        return "View";
    }

    public String prepareCreate() {
        current = new SubactividadProyecto();
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("SubactividadProyectoCreated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (SubactividadProyecto) getLazyModel().getRowData();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("SubactividadProyectoUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("SubactividadProyectoDeleted"));
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

    @FacesConverter(forClass = SubactividadProyecto.class)
    public static class SubactividadProyectoControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SubactividadProyectoController controller = (SubactividadProyectoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "subactividadProyectoController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof SubactividadProyecto) {
                SubactividadProyecto o = (SubactividadProyecto) object;
                return getStringKey(o.getIdSubactividadProyecto());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + SubactividadProyecto.class.getName());
            }
        }
    }
}

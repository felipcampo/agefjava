package jsf;

import jpa.entities.CambioResidencia;
import jsf.util.JsfUtil;
import jpa.sessions.CambioResidenciaFacade;

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

@Named("cambioResidenciaController")
@SessionScoped
public class CambioResidenciaController implements Serializable {

    private CambioResidencia current;
    private LazyDataModel<CambioResidencia> lazyModel = null;
    @EJB
    private jpa.sessions.CambioResidenciaFacade ejbFacade;

    public CambioResidenciaController() {
    }

    public CambioResidencia getSelected() {
        if (current == null) {
            current = new CambioResidencia();
        }
        return current;
    }

    public void setSelected(CambioResidencia entity) {
        current = entity;
    }

    private CambioResidenciaFacade getFacade() {
        return ejbFacade;
    }

    public LazyDataModel<CambioResidencia> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new LazyDataModel<CambioResidencia>() {
                @Override
                public List<CambioResidencia> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

                    lazyModel.setRowCount(getFacade().count(filters));
                    List<CambioResidencia> result = getFacade().getResultList(first, pageSize, sortField, sortOrder, filters);
                    if (result.isEmpty()) {
                        lazyModel.setRowCount(getFacade().count(new HashMap<String, String>()));
                    }
                    return result;
                }

                @Override
                public Object getRowKey(CambioResidencia entity) {
                    return entity.getIdCambioResidencia();
                }

                @Override
                public CambioResidencia getRowData(String rowKey) {
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
        current = (CambioResidencia) getLazyModel().getRowData();
        return "View";
    }

    public String prepareCreate() {
        current = new CambioResidencia();
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("CambioResidenciaCreated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (CambioResidencia) getLazyModel().getRowData();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("CambioResidenciaUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("CambioResidenciaDeleted"));
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

    @FacesConverter(forClass = CambioResidencia.class)
    public static class CambioResidenciaControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CambioResidenciaController controller = (CambioResidenciaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cambioResidenciaController");
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
            if (object instanceof CambioResidencia) {
                CambioResidencia o = (CambioResidencia) object;
                return getStringKey(o.getIdCambioResidencia());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CambioResidencia.class.getName());
            }
        }
    }
}

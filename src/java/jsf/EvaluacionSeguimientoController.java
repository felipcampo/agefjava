package jsf;

import jpa.entities.EvaluacionSeguimiento;
import jsf.util.JsfUtil;
import jpa.sessions.EvaluacionSeguimientoFacade;

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

@Named("evaluacionSeguimientoController")
@SessionScoped
public class EvaluacionSeguimientoController implements Serializable {

    private EvaluacionSeguimiento current;
    private LazyDataModel<EvaluacionSeguimiento> lazyModel = null;
    @EJB
    private jpa.sessions.EvaluacionSeguimientoFacade ejbFacade;

    public EvaluacionSeguimientoController() {
    }

    public EvaluacionSeguimiento getSelected() {
        if (current == null) {
            current = new EvaluacionSeguimiento();
        }
        return current;
    }

    public void setSelected(EvaluacionSeguimiento entity) {
        current = entity;
    }

    private EvaluacionSeguimientoFacade getFacade() {
        return ejbFacade;
    }

    public LazyDataModel<EvaluacionSeguimiento> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new LazyDataModel<EvaluacionSeguimiento>() {
                @Override
                public List<EvaluacionSeguimiento> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

                    lazyModel.setRowCount(getFacade().count(filters));
                    List<EvaluacionSeguimiento> result = getFacade().getResultList(first, pageSize, sortField, sortOrder, filters);
                    if (result.isEmpty()) {
                        lazyModel.setRowCount(getFacade().count(new HashMap<String, String>()));
                    }
                    return result;
                }

                @Override
                public Object getRowKey(EvaluacionSeguimiento entity) {
                    return entity.getIdEvaluacionSeguimiento();
                }

                @Override
                public EvaluacionSeguimiento getRowData(String rowKey) {
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
        current = (EvaluacionSeguimiento) getLazyModel().getRowData();
        return "View";
    }

    public String prepareCreate() {
        current = new EvaluacionSeguimiento();
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EvaluacionSeguimientoCreated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (EvaluacionSeguimiento) getLazyModel().getRowData();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EvaluacionSeguimientoUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("EvaluacionSeguimientoDeleted"));
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

    @FacesConverter(forClass = EvaluacionSeguimiento.class)
    public static class EvaluacionSeguimientoControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EvaluacionSeguimientoController controller = (EvaluacionSeguimientoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "evaluacionSeguimientoController");
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
            if (object instanceof EvaluacionSeguimiento) {
                EvaluacionSeguimiento o = (EvaluacionSeguimiento) object;
                return getStringKey(o.getIdEvaluacionSeguimiento());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EvaluacionSeguimiento.class.getName());
            }
        }
    }
}

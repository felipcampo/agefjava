package jsf;

import jpa.entities.ContactoEmergencia;
import jsf.util.JsfUtil;
import jpa.sessions.ContactoEmergenciaFacade;

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

@Named("contactoEmergenciaController")
@SessionScoped
public class ContactoEmergenciaController implements Serializable {

    private ContactoEmergencia current;
    private LazyDataModel<ContactoEmergencia> lazyModel = null;
    @EJB
    private jpa.sessions.ContactoEmergenciaFacade ejbFacade;

    public ContactoEmergenciaController() {
    }

    public ContactoEmergencia getSelected() {
        if (current == null) {
            current = new ContactoEmergencia();
        }
        return current;
    }

    public void setSelected(ContactoEmergencia entity) {
        current = entity;
    }

    private ContactoEmergenciaFacade getFacade() {
        return ejbFacade;
    }

    public LazyDataModel<ContactoEmergencia> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new LazyDataModel<ContactoEmergencia>() {
                @Override
                public List<ContactoEmergencia> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {

                    lazyModel.setRowCount(getFacade().count(filters));
                    List<ContactoEmergencia> result = getFacade().getResultList(first, pageSize, sortField, sortOrder, filters);
                    if (result.isEmpty()) {
                        lazyModel.setRowCount(getFacade().count(new HashMap<String, String>()));
                    }
                    return result;
                }

                @Override
                public Object getRowKey(ContactoEmergencia entity) {
                    return entity.getIdContactoEmergencia();
                }

                @Override
                public ContactoEmergencia getRowData(String rowKey) {
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
        current = (ContactoEmergencia) getLazyModel().getRowData();
        return "View";
    }

    public String prepareCreate() {
        current = new ContactoEmergencia();
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("ContactoEmergenciaCreated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/properties/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (ContactoEmergencia) getLazyModel().getRowData();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("ContactoEmergenciaUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/properties/Bundle").getString("ContactoEmergenciaDeleted"));
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

    @FacesConverter(forClass = ContactoEmergencia.class)
    public static class ContactoEmergenciaControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContactoEmergenciaController controller = (ContactoEmergenciaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contactoEmergenciaController");
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
            if (object instanceof ContactoEmergencia) {
                ContactoEmergencia o = (ContactoEmergencia) object;
                return getStringKey(o.getIdContactoEmergencia());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ContactoEmergencia.class.getName());
            }
        }
    }
}

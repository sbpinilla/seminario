package seminario.generate.converter;

import seminario.entidades.Acudiente;
import seminario.generate.facade.AcudienteFacade;
import seminario.generate.controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "acudienteConverter")
public class AcudienteConverter implements Converter {

    private AcudienteFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.getEjbFacade().find(getKey(value));
    }

    java.math.BigDecimal getKey(String value) {
        java.math.BigDecimal key;
        key = new java.math.BigDecimal(value);
        return key;
    }

    String getStringKey(java.math.BigDecimal value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Acudiente) {
            Acudiente o = (Acudiente) object;
            return getStringKey(o.getIdAcudiente());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Acudiente.class.getName()});
            return null;
        }
    }

    private AcudienteFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(AcudienteFacade.class).get();
        return this.ejbFacade;
    }
}

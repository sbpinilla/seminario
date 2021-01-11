package seminario.generate.converter;

import seminario.entidades.Hora;
import seminario.generate.facade.HoraFacade;
import seminario.generate.controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "horaConverter")
public class HoraConverter implements Converter {

    private HoraFacade ejbFacade;

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
        if (object instanceof Hora) {
            Hora o = (Hora) object;
            return getStringKey(o.getIdHora());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Hora.class.getName()});
            return null;
        }
    }

    private HoraFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(HoraFacade.class).get();
        return this.ejbFacade;
    }
}

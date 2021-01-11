package seminario.generate.converter;

import seminario.entidades.EstudianteAcudiente;
import seminario.generate.facade.EstudianteAcudienteFacade;
import seminario.generate.controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "estudianteAcudienteConverter")
public class EstudianteAcudienteConverter implements Converter {

    private EstudianteAcudienteFacade ejbFacade;

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
        if (object instanceof EstudianteAcudiente) {
            EstudianteAcudiente o = (EstudianteAcudiente) object;
            return getStringKey(o.getIdEstudianteAcudiente());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EstudianteAcudiente.class.getName()});
            return null;
        }
    }

    private EstudianteAcudienteFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(EstudianteAcudienteFacade.class).get();
        return this.ejbFacade;
    }
}

package be.ac.ucl.tele.yasmim.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="GenderNumberToString")
public class GenderNumberToString implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.equals("male"))
            return new Integer(1);
        else
            return new Integer(0);
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Integer gender = (Integer) value;
        if(gender == 1)
            return "male";
        else
            return "female";
    }
}
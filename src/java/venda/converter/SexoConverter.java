/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venda.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter("sexoConverter")

public class SexoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String sexo = "";
        if (value != null) {

            sexo = String.valueOf(value);
            switch (sexo) {
                case "M":
                    sexo = "Masculino";
                    break;
                case "F":
                    sexo = "Femenino";
                    break;
            }
        }
        return sexo;
    }

   

}

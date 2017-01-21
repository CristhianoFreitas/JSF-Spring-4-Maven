package br.com.jsf.web.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("BigDecimalConverter")
public class BigDecimalConverter implements Converter {

  public Object getAsObject(final FacesContext context, final UIComponent component, String value)
      throws ConverterException {

    BigDecimal result;

    if (value.equals("") || value.equals("R$ 0,00") || value.equals("0,00 %")) {
      result = null;
    } else {
      value = value.replace("R$", "");
      value = value.replace("%", "");
      value = value.trim();
      value = value.replace(".", "");
      value = value.replace(",", ".");

      try {
        result = new BigDecimal(value);
      } catch (final NumberFormatException e) {
        String nome = "";

        if (component.getAttributes().get("label") == null) {
          nome = component.getClientId();
        } else {
          nome = component.getAttributes().get("label").toString();
        }
        final FacesMessage msg =
            new FacesMessage(nome + ": Formato invalido.", "Digite um formato valido");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ConverterException(msg);
      }
    }

    return result;
  }

  public String getAsString(final FacesContext arg0, final UIComponent arg1, final Object value)
      throws ConverterException {
    final String result = NumberFormat.getCurrencyInstance().format(((BigDecimal) value).doubleValue());
    return result;
  }
}

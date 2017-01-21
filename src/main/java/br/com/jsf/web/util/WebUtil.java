package br.com.jsf.web.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.ProjectStage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

public class WebUtil {

	protected static ClassLoader getCurrentClassLoader(final Object defaultObject) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}

	public static String getMessageResourceString(final String bundleName, final String key, final Object params[],
			final Locale locale) {
		String text = null;
		final ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));

		text = bundle.getString(key);
		if (params != null) {
			final MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}

		return text;
	}

	public static FacesMessage getMessage(final String messageId, final Object params[],
			final FacesMessage.Severity severity) {
		if (messageId == null) {
			// FIXME - REMOVER APOS ARRUMAR PROBLEMA DE key null not found !
			// System.out.println("getMessage() recebeu messageId nulo !!!");
			(new Exception()).printStackTrace();
		}

		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final String bundleName = facesContext.getApplication().getMessageBundle();

		if (bundleName != null && messageId != null) {
			String summary = null;
			String detail = null;
			final Locale locale = facesContext.getViewRoot().getLocale();
			final ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));

			summary = bundle.getString(messageId);

			try {
				detail = bundle.getString(messageId + "_detail");
			} catch (final MissingResourceException e) {
			}

			if (summary != null) {
				MessageFormat mf = null;
				if (params != null) {
					mf = new MessageFormat(summary, locale);
					summary = mf.format(params, new StringBuffer(), null).toString();
				}
				if (detail != null && params != null) {
					mf.applyPattern(detail);
					detail = mf.format(params, new StringBuffer(), null).toString();
				}
				return (new FacesMessage(severity, summary, detail));
			}
		}
		return new FacesMessage(severity, "<Erro> " + messageId + " <Erro>", null);
	}

	public static String getMessage(final String messageId, final Object params[]) {
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final String bundleName = facesContext.getApplication().getMessageBundle();

		return getMessage(messageId, params, facesContext, bundleName);
	}

	public static String getLabel(final String labelId, final Object params[]) {
		final String bundleName = "label_pt_BR";

		return getMessage(labelId, params, FacesContext.getCurrentInstance(), bundleName);
	}

	public static String getMensagemSemTratamento(final String messageId, final Object params[]) {

		String resultado;
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final String bundleName = facesContext.getApplication().getMessageBundle();
		final Locale locale = facesContext.getViewRoot().getLocale();
		final ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));

		resultado = bundle.getString(messageId);

		if (resultado != null && params != null) {
			MessageFormat mf = null;
			mf = new MessageFormat(resultado, locale);
			resultado = mf.format(params, new StringBuffer(), null).toString();
		}

		return resultado;

	}

	private static String getMessage(final String messageId, final Object[] params, final FacesContext facesContext,
			final String bundleName) {
		if (bundleName != null && messageId != null) {
			String summary = null;
			String detail = null;
			final Locale locale = facesContext.getViewRoot().getLocale();
			final ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));

			try {
				summary = bundle.getString(messageId);
			} catch (final MissingResourceException e) {
				summary = "No foi possvel identificar o erro ocorrido. Favor contactar os Administradores!";
			}

			try {
				detail = bundle.getString(messageId + "_detail");
			} catch (final MissingResourceException e) {
			}

			if (summary != null) {
				MessageFormat mf = null;
				if (params != null) {
					mf = new MessageFormat(summary, locale);
					summary = mf.format(params, new StringBuffer(), null).toString();
				}
				if (detail != null && params != null) {
					mf.applyPattern(detail);
					detail = mf.format(params, new StringBuffer(), null).toString();
				}
				// Caso no encontre a mensagem, retorna o nome dela
				if (summary != null) {
					return summary;
				}
			}
		}
		// Caso no encontre a mensagem, retorna aviso
		if (ProjectStage.Development.equals(FacesContext.getCurrentInstance().getApplication().getProjectStage())) {
			// Mensagem "suja" exibida apenas caso o sistema esteja em status de
			// desenvolvimento.
			return "Erro no Sistema, a operao no pode ser realizada. Message key not registered: " + messageId;
		} else {
			// Caso esteja em produo, retorna mensagem "limpa":
			return "Erro no Sistema, a operao no pode ser realizada.";
		}
	}

	/**
	 * Retorna apenas caracteres numricos e letras de uma dada String.
	 *
	 * @param value
	 * @return string
	 */
	public static String apenasAlfanumericos(final String value) {
		return value.replaceAll("[^0-9a-zA-Z]", "");
	}

	/**
	 * Retorna apenas caracteres numricos de uma dada String.
	 *
	 * @param value
	 * @return string
	 */
	public static String apenasNumericos(final String value) {
		return value.replaceAll("[^0-9]", "");
	}

	/**
	 * Retorna o valor sem espaos afrente e atras caso no seja nulo
	 *
	 * @param valor
	 * @return
	 */
	public static String trim(final String valor) {
		if (valor != null) {
			return valor.trim();
		} else {
			return valor;
		}
	}

	/**
	 * metodo para formatar o CPF
	 *
	 * @param cpf
	 * @return
	 */
	public static String cpf(final String cpf) {
		return formatar(cpf, "###.###.###-##");
	}

	/**
	 * Formata uma {@link String} para a mscara que foi solicitada.
	 *
	 * @param valor
	 * @param mascara
	 * @return
	 */
	public static String formatar(String valor, final String mascara) {
		if (valor == null || valor.length() == 0)
			return "";
		valor = apenasNumericos(valor);
		final StringBuffer novoValor = new StringBuffer();
		int c = 0;
		for (int m = 0; m < mascara.length(); m++) {
			if (mascara.charAt(m) == '#' && c < valor.length()) {
				novoValor.append(valor.charAt(c++));
			} else {
				if (c < valor.length()) {
					novoValor.append(mascara.charAt(m));
				}
			}
		}
		return novoValor.toString();
	}

	/**
	 * Retira formatao de uma {@link String}.
	 *
	 * @param valor
	 * @param mascara
	 * @return
	 */
	public static String removerFormatacao(String valor, final String mascara) {
		valor = apenasNumericos(valor);
		final StringBuffer novoValor = new StringBuffer();
		int c = 0;
		for (int m = 0; m < mascara.length(); m++) {
			if (mascara.charAt(m) == '#') {
				if (c < valor.length()) {
					novoValor.append(valor.charAt(c++));
				}
			}
		}
		return novoValor.toString();
	}

	public static Locale getLocale() {
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext.getViewRoot().getLocale();
	}

	public static String recuperarLabel(final String label) {
		return WebUtil.getMessageResourceString(WebConstantes.LABEL_PROPERTIES, String.valueOf(label), null,
				WebUtil.getLocale());
	}

	public static Collection<SelectItem> converterLabelCombo(final Collection<SelectItem> itemColl) {
		final Collection<SelectItem> itemCollAux = new ArrayList<SelectItem>();
		SelectItem elementAux = null;

		for (final SelectItem itemAux : itemColl) {

			elementAux = new SelectItem();
			elementAux.setValue(itemAux.getValue());
			elementAux.setLabel(recuperarLabel(itemAux.getLabel()));

			itemCollAux.add(elementAux);
		}
		return itemCollAux;
	}

}

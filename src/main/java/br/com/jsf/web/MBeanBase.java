package br.com.jsf.web;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.jsf.web.util.WebUtil;

/**
 * Proposito:
 * <p>
 * ManagedBean referente ao indice
 * </p>
 */
public abstract class MBeanBase implements Serializable {

	private static final long serialVersionUID = 1L;
	private final static String MESSAGE_ID = "messageId";
	private final static String MESSAGE_CODE = "messageCode";

	private String headerModalMsg;
	private String modalMsg;
	private String nextModalAction;

	HashMap<String, Boolean> actions = new HashMap<String, Boolean>();

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Aloca um objeto no Flash Object com o nome passado no parametro
	 * <strong>nome</strong>. Seu ciclo de vida dura apenas um redirect.
	 *
	 * @param {@link
	 * 			String} nome
	 * @param {@link
	 * 			Object} param
	 */
	public void setParameter(final String nome, final Object param) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(nome, param);
	}

	/**
	 * Recupera um objeto alocado no Flash Object com o nome passado no
	 * parametro <strong>nome</strong>.
	 *
	 * @param {@link
	 * 			String} nome
	 */
	public Object getParameter(final String nome) {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(nome);
	}

	/**
	 * Emite uma mensagem de erro genérica quando o sistema pegar uma Exception.
	 *
	 * @param {@link
	 * 			Exception}
	 */
	public void adicionarMensagemException(final Exception exception) {
		adicionarMensagemWarning("aviso", exception.getCause().getMessage(), null);

		// if (exception.getCause() instanceof CicsException) {
		// adicionarMensagemWarning("aviso", exception.getCause().getMessage(),
		// null);
		// } else if (exception.getCause() instanceof DBException ||
		// exception.getCause() instanceof SQLException) {
		// adicionarMensagemErro("erro", "sis.erroBancoDeDados", null);
		// exception.printStackTrace();
		// } else {
		// adicionarMensagemErro("erro", "sis.erroDesconhecido", null);
		// exception.printStackTrace();
		// }
	}

	/**
	 * Emite uma mensagem utilizando o título e o texto da mensagem informados
	 * nos parametros de entrada.
	 *
	 * @param FacesMessage.Severity
	 *            severity
	 * @param {@link
	 * 			String} titulo
	 * @param {@link
	 * 			String} mensagem
	 */
	private void adicionarMensagem(final FacesMessage.Severity severity, final String titulo, final String mensagem) {
		getFacesContext().addMessage("MensagensGerais", new FacesMessage(severity, titulo, mensagem));
	}

	/**
	 * Emite uma mensagem de <strong>Erro</strong> utilizando o valores já
	 * cadastrados no arquivo <strong>messages.properties</strong>. Caso não
	 * haja valores já cadastrados, ele utilizará na mensagem os valores
	 * passados. <br>
	 * O parametro <strong>parametrosDeMensagem</strong> deve ser enviado como
	 * nulo no caso da mensagem não utilizar parametros;
	 *
	 * @param {@link
	 * 			String} titulo
	 * @param {@link
	 * 			String} mensagem
	 * @param Object[]
	 *            parametros
	 */
	public void adicionarMensagemErro(final String titulo, final String mensagem, final Object[] parametros) {
		adicionarMensagem(FacesMessage.SEVERITY_ERROR, WebUtil.getMensagemSemTratamento(titulo, null),
				WebUtil.getMensagemSemTratamento(mensagem, parametros));
	}

	/**
	 * Emite uma mensagem de <strong>Warning</strong> utilizando o valores já
	 * cadastrados no arquivo <strong>messages.properties</strong>. Caso não
	 * haja valores já cadastrados, ele utilizará na mensagem os valores
	 * passados. <br>
	 * O parametro <strong>parametrosDeMensagem</strong> deve ser enviado como
	 * nulo no caso da mensagem não utilizar parametros;
	 *
	 * @param {@link
	 * 			String} titulo
	 * @param {@link
	 * 			String} mensagem
	 * @param Object[]
	 *            parametros
	 */
	public void adicionarMensagemWarning(final String titulo, final String mensagem, final Object[] parametros) {
		adicionarMensagem(FacesMessage.SEVERITY_WARN, WebUtil.getMensagemSemTratamento(titulo, null),
				WebUtil.getMensagemSemTratamento(mensagem, parametros));
	}

	/**
	 * Emite uma mensagem de <strong>Informação</strong> utilizando o valores já
	 * cadastrados no arquivo <strong>messages.properties</strong>. Caso não
	 * haja valores já cadastrados, ele utilizará na mensagem os valores
	 * passados. <br>
	 * O parametro <strong>parametrosDeMensagem</strong> deve ser enviado como
	 * nulo no caso da mensagem não utilizar parametros;
	 *
	 * @param {@link
	 * 			String} titulo
	 * @param {@link
	 * 			String} mensagem
	 * @param Object[]
	 *            parametros
	 */
	public void adicionarMensagemInfo(final String titulo, final String mensagem, final Object[] parametros) {
		adicionarMensagem(FacesMessage.SEVERITY_INFO, WebUtil.getMensagemSemTratamento(titulo, null),
				WebUtil.getMensagemSemTratamento(mensagem, parametros));
	}

	/**
	 * Metodo responsavel em criar uma lista de {@link Map} contendo dois campos
	 * (messageId e messadeCode) em cada map
	 *
	 * @see MBeanBase#getDecodingMessage(String)
	 * @param codedMenssages
	 *            lista com as mensagens
	 * @return lista de {@link Map}
	 */
	public static Collection<Map<String, String>> getDecodingMessage(final String[] codedMenssages) {
		if (codedMenssages == null) {
			return null;
		}

		final Collection<Map<String, String>> messages = new ArrayList<Map<String, String>>();

		for (final String s : codedMenssages) {
			messages.add(getDecodingMessage(s));
		}

		return messages;
	}

	/**
	 * Método responsável por limpar as validações dem campos (Field
	 * Validators). Campos estes circundados de vermelho.
	 *
	 */
	public void limparValidacoesCampos() {
		RequestContext.getCurrentInstance().reset("form1");
	}

	/**
	 * Metodo responsavel em criar um {@link Map} contendo dois campos
	 * (messageId e messadeCode) que sero utilizados para exibir a mensagem de
	 * erro referente ao campo validado.
	 *
	 * @param codedMenssage
	 *            String contendo a mensagem e o identificador campo no formato
	 *            messageId#messadeCode<BR>
	 *            Ex:<BR>
	 *            form1:txtAusencia#msg_ausencia_tipo_unico <BR>
	 * @return {@link Map} contendo dois campos (messageId e messadeCode)<BR>
	 *         Caso seja passado no formato
	 *         <b>form1:txtAusencia#msg_ausencia_tipo_unico</b> sera retornado
	 *         {messageId=form1:txtAusencia,
	 *         messadeCode=msg_ausencia_tipo_unico} e <BR>
	 *         Caso seja passado no formato <b>msg_ausencia_tipo_unico</b> sera
	 *         retornado {messageId=null, messadeCode=msg_ausencia_tipo_unico}
	 */
	public static Map<String, String> getDecodingMessage(final String codedMenssage) {
		final Map<String, String> mapMessage = new HashMap<String, String>();

		if (codedMenssage.isEmpty()) {
			mapMessage.put(MESSAGE_ID, null);
			mapMessage.put(MESSAGE_CODE, null);

			return mapMessage;
		}

		final String[] arr = codedMenssage.split("#");

		if (arr.length == 2) {
			mapMessage.put(MESSAGE_ID, arr[0]);
			mapMessage.put(MESSAGE_CODE, arr[1]);
		} else {
			mapMessage.put(MESSAGE_ID, null);
			mapMessage.put(MESSAGE_CODE, arr[0]);
		}
		return mapMessage;
	}

	/**
	 * Metodo utilitario para limpeza de campos de um objeto qualquer. Soluciona
	 * o problema de campos com o texto "null" na tela.
	 *
	 * @param object
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void cleanEmptyFields(final Object object) throws Exception {
		final Class cls = object.getClass();

		final Field fieldlist[] = cls.getDeclaredFields();
		for (final Field fld : fieldlist) {
			try {
				final String fieldSuffix = fld.getName().substring(0, 1).toUpperCase() + fld.getName().substring(1);

				final Method get = cls.getMethod("get" + fieldSuffix, new Class[0]);
				// //System.out.println(get.invoke(object, new Object[0]));
				final Object value = get.invoke(object, new Object[0]);
				if (new Integer(0).equals(value) || "null".equals(value) || new Short("0").equals(value)) {

					final Method set = cls.getMethod("set" + fieldSuffix, fld.getType());
					final Object arglist[] = new Object[1];
					arglist[0] = null;
					set.invoke(object, arglist);
				}
			} catch (final NoSuchMethodException e) {
				// Exececao lanada caso no exista getter ou setter de alguma
				// propriedade do
				// objeto. Nesse caso, ignota a propriedade
			}
		}
	}

	public String getHeaderModalMsg() {
		return headerModalMsg;
	}

	public String getModalMsg() {
		return modalMsg;
	}

	/**
	 * Metodo utilitario para adicionar mensagem de validacao em um campo
	 * especfico via MBean.
	 *
	 * @param fieldId
	 *            id do campo na tela que devera ser marcado como invalido Ex:
	 *            <BR>
	 *            "form1:banco"
	 * @param fieldLabel
	 *            label do campo que sera exibido na mensagem de erro de
	 *            validacao Ex:<BR>
	 *            "lbl_Iptu_Detalhe_Banco"
	 */
	public void addFieldValidationMessage(final String fieldId, final String fieldLabel) {
		final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				WebUtil.getLabel(fieldLabel, null) + ": " + WebUtil.getMessage("valorInvalido", null), null);
		addFieldErrorMessage(fieldId, facesMessage);
	}

	/**
	 * Metodo para validacao de campos obrigatrios. Trata : - null - String (no
	 * aceita Strings vazias - Integer, BigDecimal, Long (no aceita 0)
	 *
	 * @param fieldValue
	 * @param fieldToValidateId
	 *            - id de tela do campo
	 * @param fieldToValidateLabel
	 *            - label de tela do campo
	 * @return
	 */
	public Boolean validateRequiredField(final Object fieldValue, final String fieldToValidateId,
			final String fieldToValidateLabel) {
		Boolean isValid = true;

		if (fieldValue == null) {
			isValid = false;
		}

		if (fieldValue instanceof String) {
			if ("".equals(fieldValue)) {
				isValid = false;
			}
		}

		if (fieldValue instanceof Integer) {
			if (new Integer(0).equals(fieldValue)) {
				isValid = false;
			}
		}

		if (fieldValue instanceof BigDecimal) {
			if (BigDecimal.ZERO.equals(fieldValue)) {
				isValid = false;
			}
		}

		if (fieldValue instanceof Long) {
			if (new Long(0).equals(fieldValue)) {
				isValid = false;
			}
		}

		if (!isValid) {
			addFieldValidationMessage("form1:" + fieldToValidateId, fieldToValidateLabel);
		}

		return isValid;
	}

	/**
	 * Metodo utilitario para adicionar mensagem a um campo especfico
	 *
	 * @param fieldId
	 *            id do campo na tela que devera ser marcado como invalido Ex:
	 *            <BR>
	 *            "form1:banco"
	 * @param facesMessage
	 */
	public void addFieldErrorMessage(final String fieldId, final String messageId) {
		addFieldErrorMessage(fieldId,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, WebUtil.getMessage(messageId, null), null));
	}

	/**
	 * Metodo utilitario para adicionar mensagem a um campo especfico
	 *
	 * @param fieldId
	 *            id do campo na tela que devera ser marcado como invalido Ex:
	 *            <BR>
	 *            "form1:banco"
	 * @param facesMessage
	 */
	public void addFieldErrorMessage(String fieldId, final FacesMessage facesMessage) {
		getFacesContext().addMessage(fieldId, facesMessage);
		if (!fieldId.contains("form1:")) {
			fieldId = "form1:" + fieldId;
		}
		final UIInput componente = (UIInput) getFacesContext().getViewRoot().findComponent(fieldId);
		componente.setValid(false);
	}

	/**
	 * Metodo para redirecionar de acordo com a acao
	 *
	 * @return
	 */
	public String redirectNextModalAction() {
		final String nextAction = nextModalAction;
		nextModalAction = null;
		return nextAction;
	}

	/**
	 * Metodo para retornar a query string
	 *
	 * @param parameter
	 * @return
	 */
	public String getQueryString(final String parameter) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parameter);
	}

	// TODO
	// /**
	// * Metodo para retornar um ResourceBundle
	// *
	// * @return
	// */
	// @Produces
	// public ResourceBundle getResourceBundle(final String bundleName) {
	// return ResourceBundle.getBundle(bundleName);
	// }
}

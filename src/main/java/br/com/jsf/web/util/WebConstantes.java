package br.com.jsf.web.util;

public class WebConstantes {

	public static final String PAGINA_ERRO = "/error.jsp";
	public static final String PAGINA_TIMEOUT = "/timeout.jsp";
	public static final String PAGINA_AUTENTICACAO = "/AuthenticationServlet";
	public static final String PAGINA_INICIAL = "/faces/principal.jsf";

	public static final String MENU_REDIRECT_KEY = "page";

	public static final String OK = "ok";
	public static final String ERRO = "error";

	public static final String LABEL_PROPERTIES = "META-INF/label";

	public static final String regExpCNPJ = "^\\d{2}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2}$";
	public static final String regExpCNPJOnlyNumber = "^\\d{14}$";
	public static final String regExpCPF = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$";
	public static final String regExpCPFOnlyNumber = "^\\d{11}$";
	public static final String regExpCEP = "^\\d{5}\\-?\\d{3}$";
	public static final String regExpCEPOnlyNumber = "^\\d{8}$";
	public static final String regExpDateFull = "^\\d{2}/\\d{2}/\\d{4}$";
	public static final String regExpDateCompetence = "^\\d{2}/\\d{4}$";
	public static final String regExpTelefone = "^[0-9]{2}-[0-9]{4}-[0-9]{4}$";
	public static final String regExpTelefoneTela = "^(([\\(]{1}[0-9]{2}[\\)]{1}){0,1}[0-9]{3,5}[\\-]{1}){0,1}[0-9]{4}$";
	public static final String regExpTelefoneOnlyNumber = "^\\d{10}$";
	public static final String regExpEmail = "^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$";
	public static final String regExpURL = "^((http:\\/\\/www\\.)|(www\\.)|(http:\\/\\/))[a-zA-Z0-9._-]+\\.[a-zA-Z.]{2,5}$";
	public static final String regExpEmpty = ".*[^\\s].*";
	public static final String regExpDescritivo = "[a-zA-Z0-9@]+";
	public static final String regExpTAreaAspasSimples250 = "[^']{0,250}";
	public static final String regExpTextAspasSimples100 = "[^']{0,100}";
	public static final String regExpTextAspasSimplesPtoVirgula100 = "[^';]{0,100}";

	public static final int COMBO_ANO_MES_ANO_INICIO = 2013;
	public static final int COMBO_ANO_MES_MES_INICIO = 1;

	public static final String CONTENT_TYPE_PDF = "application/pdf";
	public static final String CONTENT_TYPE_CSV = "text/txt";

}

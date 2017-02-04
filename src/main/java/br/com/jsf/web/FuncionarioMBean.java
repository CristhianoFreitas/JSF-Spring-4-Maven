package br.com.jsf.web;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jsf.model.Funcionario;
import br.com.jsf.service.UserService;

@Component
@ViewScoped
public class FuncionarioMBean extends MBeanBase {

    private static final long serialVersionUID = 1L;

    private static final String CONSULTA = "funcionarioConsulta?faces-redirect=true";
    private static final String DETALHE = "funcionarioDetalhe?faces-redirect=true";

    private Funcionario funcionario;
    private List<Funcionario> funcionarios;

    @Autowired
    UserService funcionarioService;

    @PostConstruct
    public void init() {
	limpar();
    }

    public void limpar() {
	this.funcionario = new Funcionario();
	this.funcionarios = Collections.emptyList();

	verConteudoLista = false;
    }
    
    public void filtrar() {
	try {
	    funcionarios = funcionarioService.listar();
	    verConteudoLista = true;
	} catch (final Exception e) {
	    e.printStackTrace();
	}
    }

    public String salvar() {
	try {
	    funcionarioService.salvar(funcionario);
	    
            adicionarMensagemInfo("header_sucesso", "salvar_sucesso", null);
            verConteudoLista = false;
            return CONSULTA;
            
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String novo() {
	limpar();
	return DETALHE;
    }
    
    public String voltar() {
	return CONSULTA;
    }

    public String detalhe(final Funcionario funcionario) {
	try {
	    this.funcionario = funcionario;
	} catch (final Exception e) {
	    e.printStackTrace();
	}
	return DETALHE;
    }

    public String excluir(final Funcionario funcionario) {
	try {
	    funcionarioService.excluir(funcionario);
	    
            adicionarMensagemInfo("header_sucesso", "excluir_sucesso", null);
            return CONSULTA;
            
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public Funcionario getFuncionario() {
	return funcionario;
    }

    public void setFuncionario(final Funcionario funcionario) {
	this.funcionario = funcionario;
    }

    public List<Funcionario> getFuncionarios() {
	return funcionarios;
    }

    public void setFuncionarios(final List<Funcionario> funcionarios) {
	this.funcionarios = funcionarios;
    }

    public UserService getUserService() {
	return funcionarioService;
    }

    public void setUserService(final UserService userService) {
	this.funcionarioService = userService;
    }

}

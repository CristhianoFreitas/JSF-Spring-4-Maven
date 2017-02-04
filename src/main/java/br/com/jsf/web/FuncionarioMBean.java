package br.com.jsf.web;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jsf.model.Funcionario;
import br.com.jsf.service.FuncionarioService;

@Component
@ViewScoped
public class FuncionarioMBean extends MBeanBase {

    private static final long serialVersionUID = 1L;

    private static final String CONSULTA = "funcionarioConsulta";
    private static final String DETALHE = "funcionarioDetalhe";

    private Funcionario funcionario;
    private List<Funcionario> funcionarios;

    @Autowired
    FuncionarioService funcionarioService;

    @PostConstruct
    public void init() {
	limpar();
    }

    public void limpar() {
	this.funcionario = new Funcionario();
	this.funcionarios = Collections.emptyList();

	verConteudoLista = false;
    }
    
    public String filtrar() {
	try {
	    funcionarios = funcionarioService.listar();
	    verConteudoLista = true;
	    return CONSULTA;
	} catch (final Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    public String salvar() {
	try {
	    funcionarioService.salvar(funcionario);
	    
            adicionarMensagemInfo("sucesso", "salvar_sucesso", null);
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
	    
            adicionarMensagemInfo("sucesso", "excluir_sucesso", null);
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

}

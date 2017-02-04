package br.com.jsf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class Funcionario {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String nome;

    @Column(name = "surname", unique = true, nullable = false)
    private String sobrenome;

    public int getId() {
	return id;
    }

    public void setId(final int id) {
	this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
	return sobrenome;
    }

    public void setSobrenome(final String sobrenome) {
	this.sobrenome = sobrenome;
    }

}

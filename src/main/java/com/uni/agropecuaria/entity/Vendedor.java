package com.uni.agropecuaria.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Vendedor implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Classificacao")
	@SequenceGenerator(name = "seq_Classificacao", sequenceName = "s_Classificacao", allocationSize = 1)
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(length = 11, nullable = false)
	private String	 cpf;
	
	@OneToMany(mappedBy="vendedor")
	private Collection<Pedido> vendas;
	
	public Vendedor(){}
	
	public Vendedor(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Collection<Pedido> getVendas() {
		return vendas;
	}

	public void setVendas(Collection<Pedido> vendas) {
		this.vendas = vendas;
	}
	
	
//	public void addVenda(Pedido venda){
//		this.vendas.add(venda);
//	}
}

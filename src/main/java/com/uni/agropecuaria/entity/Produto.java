package com.uni.agropecuaria.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import com.uni.agropecuaria.types.TipoProduto;

@Entity
public class Produto implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Classificacao")
	@SequenceGenerator(name = "seq_Classificacao", sequenceName = "s_Classificacao", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
		
	@Column(nullable = false)
	private TipoProduto tipo;
	
	@Column(nullable = false)
	private String fornecedor;
	
	@Column(nullable = false)
	private int estoque;
	
	@Column(nullable = false)
	private double preco;
	
	@ManyToMany
	private Collection<Pedido> vendas;
	
	public Produto(){}

	public Produto(String nome, TipoProduto tipo, String fornecedor, int estoque, double preco) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.fornecedor = fornecedor;
		this.estoque = estoque;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Collection<Pedido> getVendas() {
		return vendas;
	}

	public void setVendas(Collection<Pedido> vendas) {
		this.vendas = vendas;
	}
}

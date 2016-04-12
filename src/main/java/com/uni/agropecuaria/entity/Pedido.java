package com.uni.agropecuaria.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
public class Pedido implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_Classificacao")
	@SequenceGenerator(name = "seq_Classificacao", sequenceName = "s_Classificacao", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false)
	private String data;
	
	@Column(nullable = false)
	private Integer quantidadeItens;
	
	@Column(nullable = false)
	private double valorTotal;
	
	@ManyToOne
	@JoinColumn(name="cliente_id", nullable=false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="vendedor_id", nullable=false)
	private Vendedor vendedor;
	
	@ManyToMany
	private Collection<Produto> medicamentos;
	
	public Pedido(){}

	public Pedido(Vendedor vendedor) {
		super();
		this.vendedor = vendedor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(Integer quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Collection<Produto> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(Collection<Produto> medicamentos) {
		this.medicamentos = medicamentos;
	}
	

//	public boolean addMedicamento(Medicamento medicamento){
//		if(medicamento.getEstoque() > 0){
//			this.medicamentos.add(medicamento);
//			medicamento.vendido();
//			this.valorTotal = this.valorTotal + medicamento.getPreco();
//			return true;
//		} else {
//			return false;
//		}
//		
//	}

}

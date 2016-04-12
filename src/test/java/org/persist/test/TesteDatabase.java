package org.persist.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.uni.agropecuaria.entity.Cliente;
import com.uni.agropecuaria.entity.Produto;
import com.uni.agropecuaria.entity.Pedido;
import com.uni.agropecuaria.entity.Vendedor;
import com.uni.agropecuaria.types.TipoProduto;

public class TesteDatabase {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AGROPECUARIA");
		EntityManager em = emf.createEntityManager();

		Cliente cliente1 = new Cliente("Henrique", "12345678901");
		Cliente cliente2 = new Cliente("Cassia", "10987654321");

		Vendedor vendedor1 = new Vendedor("Douglas", "12312312300");
		Vendedor vendedor2 = new Vendedor("Bruna", "00321321321");

		Produto produto1 = new Produto("Giardicid Suspensão - 50ml", TipoProduto.MEDICACAO, "Cepav", 5, 45.00);
		Produto produto2 = new Produto("Ração Whiskas Carne 500g", TipoProduto.ALIMENTACAO, "Whikas", 30, 12.00);
		Produto produto3 = new Produto("Fantasia Batman Canina P", TipoProduto.ACESSORIOS, "Bloomer", 3, 15.00);
		Produto produto4 = new Produto("Sabonete Matacura 80g", TipoProduto.HIGIENE, "Matacura", 10, 7.00);

		ArrayList<Produto> produtos1 = new ArrayList<Produto>();
		ArrayList<Produto> produtos2 = new ArrayList<Produto>();
		ArrayList<Pedido> pedidos1 = new ArrayList<Pedido>();
		ArrayList<Pedido> pedidos2 = new ArrayList<Pedido>();

		int estoque;
		double valor;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);

		// Primeira venda
		Pedido pedido1 = new Pedido(vendedor1);
		pedidos1.add(pedido1);
		vendedor1.setVendas(pedidos1);

		pedido1.setCliente(cliente1);
		cliente1.setPedidos(pedidos1);

		produtos1.add(produto1);
		produto1.setVendas(pedidos1);
		estoque = produto1.getEstoque();
		estoque--;
		produto1.setEstoque(estoque);

		produtos1.add(produto2);
		produto2.setVendas(pedidos1);
		estoque = produto2.getEstoque();
		estoque--;
		produto2.setEstoque(estoque);

		valor = produto1.getPreco() + produto2.getPreco();
		pedido1.setValorTotal(valor);

		pedido1.setData(formattedDate);

		pedido1.setQuantidadeItens(pedidos1.size() + 1);

		pedido1.setMedicamentos(produtos1);

		// Segunda venda
		Pedido pedido2 = new Pedido(vendedor2);
		pedidos1.add(pedido2);
		vendedor2.setVendas(pedidos1);

		pedido2.setCliente(cliente2);
		cliente2.setPedidos(pedidos1);

		produtos2.add(produto3);
		produto3.setVendas(pedidos2);
		estoque = produto3.getEstoque();
		estoque--;
		produto3.setEstoque(estoque);
		
		produtos2.add(produto4);
		produto4.setVendas(pedidos2);
		estoque = produto4.getEstoque();
		estoque--;
		produto4.setEstoque(estoque);

		valor = produto3.getPreco() + produto4.getPreco();
		pedido2.setValorTotal(valor);

		pedido2.setQuantidadeItens(pedidos2.size() + 1);

		pedido2.setData(formattedDate);

		pedido2.setMedicamentos(produtos2);

		em.getTransaction().begin();
		em.persist(cliente1);
		em.persist(cliente2);
		em.persist(vendedor1);
		em.persist(vendedor2);
		em.persist(produto1);
		em.persist(produto2);
		em.persist(produto3);
		em.persist(produto4);
		em.persist(pedido1);
		em.persist(pedido2);
		em.getTransaction().commit();

		TypedQuery<Cliente> clienteQuery = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
		for (Cliente each : clienteQuery.getResultList()) {
			System.out.println("Nome Cliente: " + each.getNome());
		}
		
		TypedQuery<Vendedor> vendedorQuery = em.createQuery("SELECT v FROM Vendedor v", Vendedor.class);
		for (Vendedor each : vendedorQuery.getResultList()) {
			System.out.println("Nome Vendedor: " + each.getNome());
		}
		
		TypedQuery<Produto> medicamentoQuery = em.createQuery("SELECT pr FROM Produto pr", Produto.class);
		for (Produto each : medicamentoQuery.getResultList()) {
			System.out.println("Nome Medicamento: " + each.getNome());
		}
		
		TypedQuery<Pedido> pedidoQuery = em.createQuery("SELECT p FROM Pedido p", Pedido.class);
		Integer pedidoNumber = 1;
		for (Pedido each : pedidoQuery.getResultList()) {
			System.out.println("\nPedido número " + pedidoNumber);
			System.out.println("----------------------------------------------");
			System.out.println("Data e Horário: " + each.getData());
			System.out.println("Cliente: " + each.getCliente().getNome());
			System.out.println("Vendedor: " + each.getVendedor().getNome());
			System.out.println("Quantidade de itens: " + each.getQuantidadeItens());
			System.out.print("\nProdutos:");
			for(Produto eachMed : each.getMedicamentos()){
				System.out.println("\nProduto: " + eachMed.getNome());
				System.out.println("Tipo: " + eachMed.getTipo());
				System.out.println("Fornecedor: " + eachMed.getFornecedor());
				System.out.println("Valor: " + eachMed.getPreco());
			}
			
			System.out.println("\nValor Total: " + each.getValorTotal());
			
			System.out.println("----------------------------------------------");
			pedidoNumber++;
		}


		System.exit(0);
	}
}
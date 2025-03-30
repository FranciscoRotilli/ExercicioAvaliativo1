import java.util.ArrayList;

public class Clientela {
	private ArrayList<Cliente> clientes;

	public Clientela() {
		this.clientes = new ArrayList<Cliente>();
	}

	/**
	 * Adiciona cliente a Clientela
	 * @param c novo cliente
	 * @return true se adicionado; false se nao adicionado
	 */
	public boolean adicionaCliente(Cliente c) {
		for (Cliente cliente : clientes) {
			if (cliente.getCodigo() == c.getCodigo()) {
				return false;
			}
		}
		return clientes.add(c);
	}

	/**
	 * Consulta cliente da Clientela pelo codigo
	 * @param codigo codigo do cliente
	 * @return cliente com codigo correspondente; null se nao encontrar
	 */
	public Cliente consultaCliente(int codigo) {
		for (Cliente cliente : clientes) {
			if (cliente.getCodigo() == codigo) {
				return cliente;
			}
		}
		return null;
	}

	/**
	 * Consulta o cliente com a maior renda
	 * @return cliente com a maior renda; null se nao houver cliente cadastrado na Clientela
	 */
	public Cliente consultaclienteMaiorRenda() {
		if (clientes.isEmpty()) return null;
		Cliente aux = clientes.getFirst();
		for (Cliente cliente : clientes) {
			if (cliente.getRenda() > aux.getRenda()) {
				aux = cliente;
			}
		}
		return aux;
	}

	/**
	 * Consulta o cliente com mais carros voadores
	 * @return cliente com mais carros voadores; null se nao houverem clientes cadastrados na Clientela
	 */
	public Cliente consultaClienteMaisCarrosVoadores() {
		if (clientes.isEmpty()) return null;
		Cliente aux = clientes.getFirst();
		for (Cliente cliente : clientes) {
			if (cliente.contaCarrosVoadores() > aux.contaCarrosVoadores()) {
				aux = cliente;
			}
		}
		return aux;
	}
}

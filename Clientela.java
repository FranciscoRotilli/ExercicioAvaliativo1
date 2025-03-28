import java.util.ArrayList;

public class Clientela {
	private ArrayList<Cliente> clientes;

	public Clientela() {
		this.clientes = new ArrayList<Cliente>();
	}

	public boolean adicionaCliente(Cliente c) {
		for (Cliente cliente : clientes) {
			if (cliente.getCodigo() == c.getCodigo()) {
				return false;
			}
		}
		return clientes.add(c);
	}

	public Cliente consultaCliente(int codigo) {
		for (Cliente cliente : clientes) {
			if (cliente.getCodigo() == codigo) {
				return cliente;
			}
		}
		return null;
	}

	public Cliente consultaclienteMaiorRenda() {
		Cliente aux = clientes.get(0);
		for (Cliente cliente : clientes) {
			if (cliente.getRenda() > aux.getRenda()) {
				aux = cliente;
			}
		}
		return aux;
	}
}

public class CarroVoador {
	private int numero;
	private String placa;
	private double valor;
	private Cliente cliente;

	public CarroVoador(int numero, String placa, double valor) {
		this.numero = numero;
		this.placa = placa;
		this.valor = valor;
		this.cliente = null;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}

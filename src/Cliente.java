import java.util.ArrayList;

public class Cliente {
	private int codigo;
	private String nome;
	private double renda;

	private ArrayList<CarroVoador> carrosVoadores;

	public Cliente(int codigo, String nome, double renda) {
		this.codigo = codigo;
		this.nome = nome;
		this.renda = renda;
		this.carrosVoadores = new ArrayList<>();
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public double getRenda() {
		return renda;
	}

	public boolean addCarroVoador(CarroVoador c) {
		if (c == null) {
			return false;
		}
		return carrosVoadores.add(c);
	}

	public int contaCarrosVoadores() {
		return carrosVoadores.size();
	}
}

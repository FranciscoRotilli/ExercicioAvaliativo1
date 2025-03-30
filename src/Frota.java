import java.util.ArrayList;

public class Frota {
	private ArrayList<CarroVoador> frota;

	public Frota() {
		this.frota = new ArrayList<CarroVoador>();
	}

	/**
	 * Adiciona carro voador a Frota
	 * @param cv novo carro voador
	 * @return true se adicionado; false se nao adicionado
	 */
	public boolean adicionaCarroVoador(CarroVoador cv) {
		if (cv == null) {
			return false;
		}
        for (CarroVoador carroVoador : frota) {
            if (carroVoador.getNumero() == cv.getNumero() || carroVoador.getPlaca().equals(cv.getPlaca())) {
                return false;
            }
        }
		return frota.add(cv);
	}

	/**
	 * Consulta carro voador na frota com numero
	 * @param numero numero do carro voador
	 * @return carro voador com o numero correspondente; null se nao encontrar
	 */
	public CarroVoador consultaCarroVoador(int numero) {
		for (CarroVoador carroVoador : frota) {
			if (carroVoador.getNumero() == numero) {
				return carroVoador;
			}
		}
		return null;
	}

	/**
	 * Consulta carro voador na frota com numero
	 * @param placa placa do carro voador
	 * @return carro voador com o placa correspondente; null se nao encontrar
	 */
	public CarroVoador consultaCarroVoador(String placa) {
		for (CarroVoador carroVoador : frota) {
			if (carroVoador.getPlaca().equals(placa)) {
				return carroVoador;
			}
		}
		return null;
	}

	/**
	 * Consulta o carro voador com menor valor da Frota
	 * @return carro voador mais barato; null se nao houver carro voador na Frota
	 */
	public CarroVoador consultaCarroVoadorMaisBarato() {
		if (frota.isEmpty()) return null;
		CarroVoador aux = frota.getFirst();
		for (CarroVoador carroVoador : frota) {
			if (carroVoador.getValor() < aux.getValor()) {
				aux = carroVoador;
			}
		}
		return aux;
	}

	/**
	 * Realiza o processo de venda de um carro voador, cadastrando o cliente
	 * no carro voador e o carro voador nos carros voadores do cliente
	 * @param cv carro voador selecionado
	 * @param c cliente selecionado
	 * @return true se carro voador nao tiver cliente; false se carro voador ja tiver cliente
	 */
	public boolean vendeCarroVoador(CarroVoador cv, Cliente c) {
		if (cv.getCliente() == null) {
			cv.setCliente(c);
			c.addCarroVoador(cv);
			return true;
		}
		return false;
	}

	/**
	 * Retorna o carro voador com o valor mais proximo da
	 * media dos carro voadores da Frota que tenham cliente
	 * @return carro voador encontrado; null se nao houverem carros voadores com cliente cadastrados
	 */
	public CarroVoador consultaCarroVoadorValorMedia() {
		if (frota.isEmpty()) return null;
		CarroVoador aux = frota.getFirst();
		double sum = 0;
		double media = 0;
		int temCliente = 0;
		for (CarroVoador carroVoador : frota) {
			if (carroVoador.getCliente() != null) {
				sum += carroVoador.getValor();
				temCliente++;
			}
		}
		if (temCliente == 0) {
			return null;
		}
		media = sum / temCliente;
		ArrayList<CarroVoador> carrosVoadoresComCliente = new ArrayList<CarroVoador>();
		for (CarroVoador carroVoador : frota) {
			if (carroVoador.getCliente() != null) {
				carrosVoadoresComCliente.add(carroVoador);
			}
		}

		double minDiff = Math.abs(aux.getValor() - media);

		for (CarroVoador carroVoador : carrosVoadoresComCliente) {
			double diff = Math.abs(carroVoador.getValor() - media);
			if (diff < minDiff) {
				minDiff = diff;
				aux = carroVoador;
			}
		}
		return aux;
	}
}
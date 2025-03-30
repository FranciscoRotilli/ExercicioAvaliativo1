import java.util.ArrayList;

public class Frota {
	private ArrayList<CarroVoador> frota;

	public Frota() {
		this.frota = new ArrayList<CarroVoador>();
	}

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

	public CarroVoador consultaCarroVoador(int numero) {
		for (CarroVoador carroVoador : frota) {
			if (carroVoador.getNumero() == numero) {
				return carroVoador;
			}
		}
		return null;
	}

	public CarroVoador consultaCarroVoador(String placa) {
		for (CarroVoador carroVoador : frota) {
			if (carroVoador.getPlaca().equals(placa)) {
				return carroVoador;
			}
		}
		return null;
	}

	public CarroVoador consultaCarroVoadorMaisBarato() {
		CarroVoador aux = frota.getFirst();
		for (CarroVoador carroVoador : frota) {
			if (carroVoador.getValor() < aux.getValor()) {
				aux = carroVoador;
			}
		}
		return aux;
	}

	public boolean vendeCarroVoador(CarroVoador cv, Cliente c) {
		if (cv.getCliente() == null) {
			cv.setCliente(c);
			c.addCarroVoador(cv);
			return true;
		}
		return false;
	}

	public CarroVoador consultaCarroVoadorValorMedia() {
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
		media = sum / temCliente;
		ArrayList<CarroVoador> carrosVoadoresComCliente = new ArrayList<CarroVoador>();
		for (CarroVoador carroVoador : frota) {
			if (carroVoador.getCliente() != null) {
				carrosVoadoresComCliente.add(carroVoador);
			}
		}

		if (carrosVoadoresComCliente.isEmpty()) {
			return null;
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
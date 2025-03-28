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
		CarroVoador aux = frota.get(0);
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
}
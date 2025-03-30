import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class ACMEFly {
	// Atributos para redirecionamento de E/S
	private Scanner entrada = new Scanner(System.in);  // Atributo para entrada padrao (teclado)
	private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
	private final String nomeArquivoEntrada = "dadosentrada.txt";  // Nome do arquivo de entrada de dados
	private final String nomeArquivoSaida = "dadossaida.txt";  // Nome do arquivo de saida de dados
	private Clientela clientela;
	private Frota frota;

	// Redireciona Entrada de dados para arquivos em vez de teclado
	// Chame este metodo para redirecionar a leitura de dados para arquivos
	private void redirecionaEntrada() {
		try {
			BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
			entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
		} catch (Exception e) {
			System.out.println(e);
		}
		Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
		entrada.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal
	}

	// Redireciona Saida de dados para arquivos em vez da tela (terminal)
	// Chame este metodo para redirecionar a escrita de dados para arquivos
	private void redirecionaSaida() {
		try {
			PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
			System.setOut(streamSaida);             // Usa como saida um arquivo
		} catch (Exception e) {
			System.out.println(e);
		}
		Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
	}

	// Restaura Entrada padrao para o teclado
	// Chame este metodo para retornar a leitura de dados para o teclado
	private void restauraEntrada() {
		entrada = new Scanner(System.in);
	}

	// Restaura Saida padrao para a tela (terminal)
	// Chame este metodo para retornar a escrita de dados para a tela
	private void restauraSaida() {
		System.setOut(saidaPadrao);
	}

	public ACMEFly() {
		Locale.setDefault(Locale.ENGLISH);
		entrada.useLocale(Locale.ENGLISH);
		redirecionaEntrada();    // Redireciona Entrada para arquivos
		redirecionaSaida();    // Redireciona Saida para arquivos
		frota = new Frota();
		clientela = new Clientela();
	}

	public void executar() {
		cadastraCarroVoador();
		cadastraCliente();
		consultaCarroVoadorMaisBarato();
		consultaclienteMaiorRenda();
		vendeCarroVoador();
		consultaClienteMaisCarrosVoadores();
		consultaCarroVoadorPlaca();
		consultaCarroVoadorValorMedia();
	}

	public void cadastraCarroVoador() {
        int numero;
        while (true) {
            numero = entrada.nextInt();
			if (numero == -1) {
				break;
			}
            String placa = entrada.next();
            double preco = entrada.nextDouble();
            CarroVoador cv = new CarroVoador(numero, placa, preco);
            if (frota.adicionaCarroVoador(cv)) {
				System.out.println("1:" + cv.getNumero() + ":" + cv.getPlaca() + ":" + cv.getValor());
            } else {
				System.out.println("1:erro-carro repetido.");
            }
        }
    }

	public void cadastraCliente() {
        int codigo;
        while (true) {
			codigo = entrada.nextInt();
			if (codigo == -1) {
				break;
			}
			entrada.nextLine();
			String nome = entrada.nextLine();
			double renda = entrada.nextDouble();
			Cliente c = new Cliente(codigo, nome, renda);
			if (clientela.adicionaCliente(c)) {
				System.out.println("2:" + c.getCodigo() + ":" + c.getNome() + ":" + c.getRenda());
			} else {
				System.out.println("2:erro-cliente repetido.");
			}
		}
    }

	public void consultaCarroVoadorMaisBarato() {
		CarroVoador cv = frota.consultaCarroVoadorMaisBarato();
		if (cv != null) {
			System.out.println("3:"+cv.getNumero()+":"+cv.getPlaca()+":"+cv.getValor());
		} else {
			System.out.println("3:nenhum carro encontrado.");
		}
	}

	public void consultaclienteMaiorRenda() {
		Cliente c = clientela.consultaclienteMaiorRenda();
		if (c != null) {
			System.out.println("4:"+c.getCodigo()+":"+c.getNome()+":"+c.getRenda());
		} else {
			System.out.println("4:nenhum cliente encontrado.");
		}
	}

	public void vendeCarroVoador() {
		while (true) {
			int codigoCliente = entrada.nextInt();
			if (codigoCliente == -1) {
				break;
			}
			int numeroCarro = entrada.nextInt();
			Cliente c = clientela.consultaCliente(codigoCliente);
			CarroVoador cv = frota.consultaCarroVoador(numeroCarro);
			boolean clienteExiste = c != null;
			boolean carroExiste = cv != null;
			if (!clienteExiste) {
				System.out.println("5:erro-cliente inexistente.");
				continue;
			}
			if (!carroExiste) {
				System.out.println("5:erro-carro inexistente.");
				continue;
			}
			boolean venda = frota.vendeCarroVoador(cv, c);
			if (venda) {
				System.out.println("5:"+c.getNome()+":"+cv.getPlaca());
			} else {
				System.out.println("5:erro-carro possui cliente.");
			}
		}
	}

	public void consultaClienteMaisCarrosVoadores() {
		Cliente c = clientela.consultaClienteMaisCarrosVoadores();
		if (c.contaCarrosVoadores() > 0) {
			System.out.println("6:"+c.getNome()+":"+c.contaCarrosVoadores());
		} else {
			System.out.println("6:erro-nenhum cliente encontrado.");
		}
	}

	public void consultaCarroVoadorPlaca() {
		String placa = entrada.next();
		CarroVoador cv = frota.consultaCarroVoador(placa);
		if (cv == null) {
			System.out.println("7:erro-carro inexistente.");
		} else if (cv.getCliente() == null) {
			System.out.println("7:erro-carro sem cliente.");
		} else {
			System.out.println("7:"+cv.getPlaca()+":"+cv.getCliente().getNome());
		}
	}

	public void consultaCarroVoadorValorMedia() {
		CarroVoador cv = frota.consultaCarroVoadorValorMedia();
		if (cv == null) {
			System.out.println("8:erro-carro inexistente.");
		} else {
			System.out.println("8:"+cv.getNumero()+":"+cv.getPlaca()+
					":"+cv.getValor()+":"+cv.getCliente().getCodigo()+
					":"+cv.getCliente().getNome()+":"+cv.getCliente().getRenda());
		}
	}
}

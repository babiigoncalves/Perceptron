package perceptron;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Perceptron {

	private File f;
	private File f2;
	Dados dados;
	public ArrayList<Dados> listaTreino = new ArrayList<Dados>();
	public ArrayList<Dados> listaTestes = new ArrayList<Dados>();
	public double[] pesos;
	private double somatorio = 0.0;
	private double taxaAprendizado = 0.4;
	private int count = 0;
	private double limiar =0.5;
	double u;
	double w0;
	double w1;
	double w2;

    
	public void lerArquivoTreino(String path) throws IOException {
		String linhaArquivo;
		double x0, x1 = 0.0;
		int yd = 0;

		this.f = new File(path);
		BufferedReader leitor = new BufferedReader(new FileReader(this.f));
		linhaArquivo = leitor.readLine();

		while (linhaArquivo != null) {
			// linhaArquivo = leitor.readLine();

			String[] vetorTemp = linhaArquivo.split(" ");
			if (vetorTemp != null && !vetorTemp.equals(" ")) {
				try{
				x0 = Double.parseDouble(vetorTemp[0]);
				x1 = Double.parseDouble(vetorTemp[1]);
				yd = Integer.parseInt(vetorTemp[2]);
				this.dados = new Dados(x0, x1, yd);
				}catch(NumberFormatException e){}
				listaTreino.add(dados);
				linhaArquivo = leitor.readLine();
			}
		}
		leitor.close();

	}

	public void lerArquivoTestes(String path) throws IOException  {
		String linhaArquivo;
		double x0 =0.0, x1 = 0.0;
		int yd = 0;

		this.f2 = new File(path);
		BufferedReader leitor = new BufferedReader(new FileReader(this.f2));
		linhaArquivo = leitor.readLine();

		while (linhaArquivo != null) {
			// linhaArquivo = leitor.readLine();

			String[] vetorTemp = linhaArquivo.split(" ");
			if (vetorTemp != null && !vetorTemp.equals(" ")) {
				
				x0 = Double.parseDouble(vetorTemp[0]);
				x1 = Double.parseDouble(vetorTemp[1]);
				yd = Integer.parseInt(vetorTemp[2]);
				this.dados = new Dados(x0, x1, yd);

				

				listaTreino.add(dados);
				linhaArquivo = leitor.readLine();
			}
		}
		
		leitor.close();

	}
	
	
	public void menu() throws IOException {
		int opcao = 0;
	    w0 = 0;
	    w1 = 0;
	    w2= 0;
		do{
		System.out.println("Defina a opcao:");
		System.out.println("1-Carregar a base de dados");
		System.out.println("2-Treinar");
		System.out.println("3-Testar");
		Scanner in = new Scanner(System.in);
		
		opcao = in.nextInt();
		switch (opcao) {
		case 1:
			this.lerArquivoTestes("/Users/Mila/workspace/ProjetoPercp/src/perceptron/teste.txt");
			//this.lerArquivoTreino("/Users/Mila/workspace/ProjetoPercp/src/perceptron/treino.txt");
			break;
		case 2:
			this.treinar();
			break;
		case 3:
			break;

		}
		}while(opcao !=0);
	}

	public void treinar() {
		System.out.println("Entrou!");
		boolean treinou = true;
		int saida;
		double x1, x2;
		int desejado;
		int ultimaPosicaoErro = -1;

		do {
			for (Dados d : listaTreino) {
				x1 = d.x1;
				x2 = d.x2;
				desejado = d.getYd();
				//saida = executar(x1, x2);
				this.calcularSaida(dados);
				this.calcularAtivacao(dados);
				
				if (dados.getY0() != dados.getYd()) {
					this.corrigirPeso(dados);
					ultimaPosicaoErro = this.count;
					treinou = false;
				}
			}
			this.count++;
			System.out.println(count);
			if (ultimaPosicaoErro == this.count) {
				treinou = true;
			}
		} while (!treinou);

		System.out.println("Fim da fase de treinamento! Ciclos:" + this.count);
	}

	public int executar(double x1, double x2) {
		somatorio = (x1 * this.w0) + (x2 * this.w1) + ((-1) * this.w2);

		if (somatorio >= 0) {
			return 1;
		}
		return 0;
	}

	// Metodo para a correcao de pesos
	public void corrigirPeso(Dados dados) {
		int erro = 0;
		erro = dados.getYd() -dados.getY0();

		this.w0 = this.w0 + taxaAprendizado * this.dados.getX0() * erro;
		this.w1 = this.w1 + taxaAprendizado * this.dados.getX1() * erro;
		this.w2 = this.w2 + taxaAprendizado * this.dados.getX2() * erro;
	}

	private void calcularAtivacao(Dados dados){
		if(this.u >= this.limiar){
			dados.setY0(2);
		}else{
			dados.setY0(1);
		}
	}
	
	private void calcularSaida(Dados dados){
		double saida = 0.0;
		
		saida += dados.getX0() * this.w0;
		saida += dados.getX1() * this.w1;
		saida += dados.getX2() * this.w2;
		
		this.u = saida;
		
	}
	
	public int calcularErro(int saida, int desejado) {
		return desejado - saida;
	}
}

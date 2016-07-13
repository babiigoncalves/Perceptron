package perceptron;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
 
public class old {
	private File f;
	Dados dados;
	public ArrayList<Dados> listaTreino = new ArrayList<Dados>();
	public ArrayList<Dados> listaTestes = new ArrayList<Dados>();
	public double[] pesos;
    private double[] w = new double[3];
 
    // variavel responsavel pelo somatorio(rede).
    private double somatorio = 0;
 
    // variaval responsavel pelo numero maximo de ciclos
    private final int ciclos = 30;
 
    // variavel responsavel pela contagem das ciclos durante o treinamento
    private int count = 0;
 
    // declara o vetor da matriz de aprendizado
    private int[][] matrizAprendizado = new int[4][3];
 
    // METODO DE RETORNO DO CONTADOR
    public int getCount(){
 
      return this.count;
 
    }
 // metodo de inicializacao inicia o vetor da matriz de aprendizado
  old() {
	  String arquivo = "/Users/Mila/workspace/ProjetoPercep/src/perceptron/treino.txt";
    // Primeiro valor
    this.matrizAprendizado[0][0] = 0; // entrada 1
    this.matrizAprendizado[0][1] = 0; // entrada 2
    this.matrizAprendizado[0][2] = 0; // valor esperado
 
    // Segundo Valor
    this.matrizAprendizado[1][0] = 0; // entrada 1
    this.matrizAprendizado[1][1] = 1; // entrada 2
    this.matrizAprendizado[1][2] = 0; // valor esperado
 
    // terceiro valor
    this.matrizAprendizado[2][0] = 1; // entrada 1
    this.matrizAprendizado[2][1] = 0; // entrada 2
    this.matrizAprendizado[2][2] = 0; // valor esperado
 
    // quarto valor
    this.matrizAprendizado[3][0] = 1; // entrada 1
    this.matrizAprendizado[3][1] = 1; // entrada 2
    this.matrizAprendizado[3][2] = 1; // valor esperado
 
    // inicializacao dos pesos sinapticos
 
    // Peso sinaptico para primeira entrada.
    w[0] = 0;
    // Peso sinaptico para segunda entrada.
    w[1] = 0;
    // Peso sinaptico para o BIAS
    w[2]= 0;
 
}
 
  // M�todo responsavel pelo somatorio e a funcao de ativacao.
    int executar(int x1, int x2) {
 
        // Somat�rio (NET)
        somatorio = (x1 * w[0]) + (x2 * w[1]) + ((-1) * w[2]);
 
        // Fun��o de Ativa��o
        if (somatorio >= 0) {
            return 1;
        }
        return 0;
    }
 
    // M�todo para treinamento da rede
    public void treinar() {
 
        // variavel utilizada respons�vel pelo controlede treinamento recebefalso
        boolean treinou= true;
        // var�vel respons�vel para receber o valor da sa�da (y)
        int saida;
 
        // la�o usado para fazer todas as entradas
        for (int i = 0; i < matrizAprendizado.length; i++) {
            // A sa�da recebe o resultado da rede que no caso � 1 ou 0
            saida = executar(matrizAprendizado[i][0], matrizAprendizado[i][1]);
 
 
            if (saida != matrizAprendizado[i][2]) {
                // Caso a saida seja diferente do valor esperado
 
                // os pesos sinapticos serao corrigidos
                corrigirPeso(i, saida);
                // a variavel responsavel pelo controlede treinamento recebe falso
                treinou = false;
 
            }
        }
        // acrescenta uma epoca
        this.count++;
 
        // teste se houve algum erro duranteo treinamento e o numero de ciclos
        //e menor qe o definido
        if((treinou == false) && (this.count < this.ciclos)) {
            // chamada recursiva do metodo
            treinar();
 
        }
 
    }    // fim do metodo para treinamento
 
    // Metodo para a correcao de pesos
    void corrigirPeso(int i, int saida) {
 
        w[0] = w[0] + (1 * (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][0]);
        w[1] = w[1] + (1 * (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][1]);
        w[2] = w[2] + (1 * (matrizAprendizado[i][2] - saida) * (-1));
 
    }
 
    void testar() {
 
 
        System.out.println(" Teste 01 para 0 e 0 " + executar(0, 0));
 
        System.out.println(" Teste 02 para 0 e 1 " + executar(0, 1));
 
        System.out.println(" Teste 03 para 1 e 0 " + executar(1, 0));
 
        System.out.println(" Teste 04 para 0 e 0 " + executar(0, 0));
 
        System.out.println(" Teste 05 para 1 e 1 " + executar(1, 1));
 
    }
    

    public static void main(String[] arguments) {
 

        old p = new old();
 
        p.treinar();
 
        System.out.println("Para aprender o algoritmo treinou " + p.getCount() + " ciclos! \n ");
 
        p.testar();
 
    }

}
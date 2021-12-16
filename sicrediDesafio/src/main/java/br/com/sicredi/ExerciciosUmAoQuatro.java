package br.com.sicredi;

import java.math.BigDecimal;

public class ExerciciosUmAoQuatro {

	private static BigDecimal totalEleitores = new BigDecimal(1000);
	private static BigDecimal Votosvalidos = new BigDecimal(800);
	private static BigDecimal votosBrancos = new BigDecimal(150);
	private static BigDecimal votosNulos = new BigDecimal(50);

	public static void main(String[] args) {
		System.out.println(percentualVotosValidos().multiply(BigDecimal.valueOf(100)) + "% de votos válidos");
		System.out.println(percentualVotosBrancos().multiply(BigDecimal.valueOf(100)) + "% de votos brancos");
		System.out.println(percentualVotosNulos().multiply(BigDecimal.valueOf(100)) + "% de votos nulos");
		bubbleSort();
		calculaFatorial(9); /*Informar qual o fatorial quer*/
		somaMultiplos(10);  /*Informar até qual numero verificar multiplo*/
	}

	public static BigDecimal percentualVotosValidos() {
		BigDecimal percentual = BigDecimal.ZERO;
		percentual = Votosvalidos.divide(totalEleitores);
		return percentual;
	}

	public static BigDecimal percentualVotosBrancos() {
		BigDecimal percentual = BigDecimal.ZERO;
		percentual = votosBrancos.divide(totalEleitores);
		return percentual;
	}

	public static BigDecimal percentualVotosNulos() {
		BigDecimal percentual = BigDecimal.ZERO;
		percentual = votosNulos.divide(totalEleitores);
		return percentual;
	}

	public static void bubbleSort() {
		int[] v = { 5, 2, 4, 3, 0, 9, 7, 8, 1, 6 };
		ordenar(v);
		for (int num : v) {
			System.out.print(num + " ");
		}
	}

	public static void ordenar(int[] vetor) {

		for (int a = 0; a < vetor.length - 1; a++) {

			for (int b = 0; b < vetor.length - 1 - a; b++) {

				if (vetor[b] > vetor[b + 1]) {
					int c = vetor[b];
					vetor[b] = vetor[b + 1];
					vetor[b + 1] = c;
				}
			}
		}
	}

	public static void calculaFatorial(int valorFatorial) {
		System.out.println();
		int fatorando = 1;
		int valor = valorFatorial;
		for (int i = 2; i <= valor; i++) {
			fatorando *= i;
		}
		System.out.print("O fatorial de " + valor + " é igual a " + fatorando);
	}

	public static void somaMultiplos(int numero) {
		System.out.println();
		int x = 3;
		int z = 5;
		int resposta = 0;

		for(int i = 0; i < numero; i++){
		    if(i % x == 0 || i % z == 0){
		    	resposta += i;
		    }
		}

		System.out.println("A soma dos multiplos de 3 e 5 abaixo de "+ numero + ", é: " + resposta);

	}
}

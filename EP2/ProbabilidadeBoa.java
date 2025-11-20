/******************************************************************************
 *
 * CCM0118 COMPUTAÇÃO I
 * Aluno: BRUNO KLEINE MOLLICA
 * Numero USP: 14562470
 * Tarefa: EP 02 - Parte 2
 * Data: 29/09/2024
 *
 * Baseado em exercícios e programas feitos em aula retirados do Sandbox da página da disciplina.
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/
public class ProbabilidadeBoa {
    public static void main(String[] args) {


        int NMAX = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);


        for (int N = 2; N <= NMAX; N++) {
            int boasPermutacoes = 0;

            // executa T simulações para cada N
            for (int t = 0; t < T; t++) {
                // Gera uma permutação aleatória de 0 a N-1
                int[] a = new int[N];
                int i = 0;
                while (i < N) {
                    a[i] = i;
                    i++;
                }

                // embaralhamento do array
                i = N - 1;
                while (i > 0) {
                    int j = (int) (Math.random() * (i + 1));
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    i--;
                }

                // verifica se a permutação é "boa" (nenhum a[i] == i)
                boolean ehBoa = true;
                i = 0;
                while (i < N) {
                    if (a[i] == i) {
                        ehBoa = false;
                    }
                    i++;
                }

                // se a permutação é boa, aumaneta no contador
                if (ehBoa) {
                    boasPermutacoes++;
                }
            }

            // calcula qN
            double qN = (double) boasPermutacoes / T;
            System.out.println("q_" + N + " = " + qN);
        }
    }
}

/*
RESPOSTA A PERGUNTA:

Sim, os valores de qN parecem convergir para um valor específico à medida que N aumenta.
Este valor é aproximadamente é aproximadamente 0.36789 que equivale a 1/e onde e é a base dos logaritmos naturais, aproximadamente 2.718282.

*/

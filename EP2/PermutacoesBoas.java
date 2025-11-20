/******************************************************************************
 *
 * CCM0118 COMPUTAÇÃO I
 * Aluno: BRUNO KLEINE MOLLICA
 * Numero USP: 14562470
 * Tarefa: EP 02 - Parte 3
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
public class PermutacoesBoas {
    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        // gera T permutações boas
        int t = 0;
        while (t < T) {
            int[] a = new int[N];
            int i = 0;
            while (i < N) {
                a[i] = i;
                i++;
            }

            // variável para controlar se a permutação é boa
            boolean ehDeranjo = false;

            // loop para gerar uma permutação boa
            while (!ehDeranjo) {
                i = N - 1;
                while (i > 0) {
                    int j = (int) (Math.random() * (i + 1));
                    // Troca a[i] com a[j]
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    i--;
                }

                // verifica se a permutação é boa (nenhum a[i] == i)
                ehDeranjo = true;
                i = 0;
                while (i < N) {
                    if (a[i] == i) {
                        ehDeranjo = false;
                    }
                    i++;
                }
            }

            // imprime a perrmutacao
            System.out.print(t + ":");
            i = 0;
            while (i < N) {
                System.out.print(" " + a[i]);
                i++;
            }
            System.out.println();

            t++;
        }
    }
}


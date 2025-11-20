/******************************************************************************
 *
 * CCM0118 COMPUTAÇÃO I
 * Aluno: BRUNO KLEINE MOLLICA
 * Numero USP: 14562470
 * Tarefa: EP 02 - Parte 1
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
public class ProbabilidadeS {
    public static void main(String[] args) {

        int NMAX = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        for (int N = 2; N <= NMAX; N++) {
            int ocorrenciasFenomenoS = 0;

            // executa T simulações para cada N
            for (int t = 0; t < T; t++) {

                // gera uma pernutação aleatoria de 0 a N-1
                int[] a = new int[N];
                for (int i = 0; i < N; i++) {
                    a[i] = i;
                }

                // embaralhamemto
                int i = N - 1;
                while (i > 0) {
                    int indiceAleatorio = (int) (Math.random() * (i + 1));
                    // Troca a[i] com a[indiceAleatorio]
                    int temp = a[i];
                    a[i] = a[indiceAleatorio];
                    a[indiceAleatorio] = temp;
                    i--;
                }

                // inicia o rastreamento das pessoas que já deram presentes
                boolean[] presenteDado = new boolean[N];

                // vetor para registrar a ordem de recebimento dos presentes
                int[] ordemRecebimento = new int[N];
                int contadorRecebimento = 0;

                // inicia o processo com a pessoa 0
                int pessoaAtual = 0;

                // simula o processo de distribuição de presentes
                while (contadorRecebimento < N) {
                    if (!presenteDado[pessoaAtual]) {

                        // marca que a pessoa atual deu seu presente
                        presenteDado[pessoaAtual] = true;

                        // registra quem recebeu o presente
                        ordemRecebimento[contadorRecebimento] = a[pessoaAtual];
                        contadorRecebimento++;

                        // atualiza a pessoa atual para quem recebeu o presente
                        pessoaAtual = a[pessoaAtual];
                    } else {
                        // encontra a próxima pessoa que ainda não deu presente
                        int proximaPessoa = -1;
                        int j = 0;
                        while (j < N && proximaPessoa == -1) {
                            if (!presenteDado[j]) {
                                proximaPessoa = j;
                            }
                            j++;
                        }

                        // se encontrou uma pessoa que ainda não deu presente, atualiza pessoaAtual
                        if (proximaPessoa != -1) {
                            pessoaAtual = proximaPessoa;
                        } else {
                            // todas as pessoas já deram seus presentes
                            // atualiza contadorRecebimento para sair do loop
                            contadorRecebimento = N;
                        }
                    }
                }

                // verifica se a pessoa 0 recebeu seu presente por último
                if (ordemRecebimento[N - 1] == 0) {
                    ocorrenciasFenomenoS++;
                }
            }

            // calcula pN
            double pN = (double) ocorrenciasFenomenoS / T;
            System.out.println("p_" + N + " = " + pN);
        }
    }
}


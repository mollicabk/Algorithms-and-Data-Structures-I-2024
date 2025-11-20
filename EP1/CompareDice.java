/******************************************************************************
 *
 * CCM0118 COMPUTAÇÃO I
 * Aluno: BRUNO KLEINE MOLLICA
 * Numero USP: 14562470
 * Tarefa: E01 Dados Generalizados
 * Data: 08/09/2024
 *
 * Baseado em códigos feitos em aula e em específico o código CompareDiceHELP.java passado pelo professor.
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/


public class CompareDice {
    public static void main(String[] args) {

        int aa = Integer.parseInt(args[0]); //Tamanho do array correspondente ao dado A
        int bb = Integer.parseInt(args[aa + 1]); //Tamanho do array correspondente ao dado B
        int N = Integer.parseInt(args[aa + bb + 2]); //Número de lançamentos da simulação

        //Declarando e criando o array correspondente ao dado A e B
        int[] a = new int[aa];
        int[] b = new int[bb];

        //Declarando e criando as variáveis correspondente ao número de vitórias de A e B e à probabilidade de A ganhar
        int A_wins = 0, B_wins = 0;
        double prob_A;

        //Cosntruindo os arrays correspondentes aos dados A e B
        for (int i = 0; i < aa; i++)
            a[i] = Integer.parseInt(args[i + 1]);

        for (int i = 0; i < bb; i++)
            b[i] = Integer.parseInt(args[aa + i + 2]);

        // Simulação dos lançamentos
        for (int i = 0;i < N ;i++ ) {
            int lanc_a = (int) (Math.random() * aa); // Gerando um índice aleatório lanc_a para o valor a[lanc_a]
            int va = a[lanc_a]; // Valor corresponde ao lançamento i do Dado A

            int lanc_b = (int) (Math.random() * bb); // Gerando um índice aleatório lanc_b para o valor b[lanc_b]
            int vb = b[lanc_b]; // Valor corresponde ao lançamento i do Dado B

            // Verificação de quem ganhou a rodada i, caso empate ninguém ganha
            if (va > vb) A_wins++;
            else if (va < vb) B_wins++;
        }

        // Calculando a probabilidade de A ganhar
        prob_A = (double) A_wins / (A_wins + B_wins);

        // Imprimimdo os valores do dado A e B
        System.out.print("A: ");
        for (int i = 0; i < aa; i++)
            System.out.print(a[i] + " ");
        System.out.println();

        System.out.print("B: ");
        for (int i = 0; i < bb; i++)
            System.out.print(b[i] + " ");
        System.out.println();

        // Imprimimdo o numero de vitórias de A e de B e a probabilidade de A ganhar
        System.out.println("Number of A wins: " + A_wins);
        System.out.println("Number of B wins: " + B_wins);
        System.out.println("Probability that A wins: " + prob_A);

    }
}

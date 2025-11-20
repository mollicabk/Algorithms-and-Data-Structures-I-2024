/******************************************************************************
 *
 * CCM0118 COMPUTAÇÃO I
 * Aluno: Bruno Kleine Mollica
 * Numero USP: 14562470
 * Tarefa: E05 - Torres 3D
 * Data: 24/11/2024
 *
 * Baseado em exercícios feitos em aula, códigos do Sandbox, e programas do livro do Sedgewick e Wayne.
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/


public class Torres3D {

    static int N;
    static int total;
    static int[][] a;
    static boolean[][] usedKInRow;
    static boolean[][] usedKInCol;
    static boolean[][] usedRowInHeight;
    static boolean[][] usedColInHeight;
    static boolean printSolutions;

    public static void main(String[] args) {

        N = Integer.parseInt(args[0]);
        printSolutions = args.length > 1;

        a = new int[N][N];
        usedKInRow = new boolean[N][N];
        usedKInCol = new boolean[N][N];
        usedRowInHeight = new boolean[N][N];
        usedColInHeight = new boolean[N][N];

        total = 0;
        fill(0, 0);

        System.out.println("Total no: " + total);
    }

    static void fill(int i, int j) {
        if (i == N) {
            // Uma solução foi encontrada
            total++;
            if (printSolutions) {
                printSolution();
            }
            return;
        }

        for (int k = 0; k < N; k++) {
            if (!usedKInRow[i][k] && !usedKInCol[j][k] && !usedRowInHeight[k][i]
                    && !usedColInHeight[k][j]) {
                a[i][j] = k;
                usedKInRow[i][k] = true;
                usedKInCol[j][k] = true;
                usedRowInHeight[k][i] = true;
                usedColInHeight[k][j] = true;

                if (j + 1 < N) {
                    fill(i, j + 1);
                }
                else {
                    fill(i + 1, 0);
                }

                usedKInRow[i][k] = false;
                usedKInCol[j][k] = false;
                usedRowInHeight[k][i] = false;
                usedColInHeight[k][j] = false;
            }
        }
    }

    static void printSolution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(a[i][j]);
                if (j < N - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}


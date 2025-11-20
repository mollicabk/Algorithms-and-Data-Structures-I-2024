/******************************************************************************
 *
 * CCM0118 COMPUTAÇÃO I
 * Aluno: BRUNO KLEINE MOLLICA
 * Numero USP: 14562470
 * Tarefa: E04 Hanoi4
 * Data: 09/11/2024
 *
 * Baseado em códigos do livro SEDGEWICK & WAYNE, e feitos em aula disponíveis no Sandbox,
 * especificamente os programas: Hanoi.java, HanoiAula.java, HanoiAula2.java, HanoiPlain.java,
 * assim como estudei uma parte teorica de recursão pelo portal de ensino GeeksforGeeks
 * com link https://www.geeksforgeeks.org .
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class Hanoi4 {

    private static int moveCount = 0; // Contador de movimentos
    private static String moveBuffer = ""; // String para armazenar os movimentos
    private static int[] U;
    // Array para armazenar U[k] (número mínimo de movimentos para k discos com 4 torres)

    public Hanoi4(
            int maxN) { // Inicializa o array U para armazenar os movimentos mínimos para até maxN discos
        U = new int[maxN + 1];
        U[0] = 0;
        if (maxN >= 1) U[1] = 1;
        for (int n = 2; n <= maxN; n++) {
            U[n] = Integer.MAX_VALUE;
            for (int k = 1; k < n; k++) {
                int t = 2 * U[k] + ((1 << (n - k)) - 1);
                if (t < U[n]) {
                    U[n] = t;
                }
            }
        }
    }

    // função para registrar os movimentos do disco
    private static void recordMove(int disk, int toTorre) {
        moveBuffer += disk + " " + toTorre + "  ";
        moveCount++;
    }

    // função recursiva para resolver as Torres de Hanoi com quatro torres
    static void towerOfHanoi(int n, int base, int fromTorre, int toTorre, int aux1, int aux2) {
        if (n == 0) return;

        if (n == 1) {
            recordMove(base, toTorre);
            return;
        }

        // Encontrar k que minimiza 2*U[k] + T(n-k)
        int k = 1;
        int min = 2 * U[k] + ((1 << (n - k)) - 1);
        for (int i = 2; i < n; i++) {
            int t = 2 * U[i] + ((1 << (n - i)) - 1);
            if (t < min) {
                min = t;
                k = i;
            }
        }

        // Passo 1: Mover k discos da torre de origem para a torre auxiliar1
        towerOfHanoi(k, base, fromTorre, aux1, toTorre, aux2);

        // Passo 2: Mover os n-k discos restantes da torre de origem para a torre de destino usando três torres (Hanoi3)
        towerOfHanoi3(n - k, base + k, fromTorre, toTorre, aux2);

        // Passo 3: Mover os k discos da torre auxiliar1 para a torre de destino
        towerOfHanoi(k, base, aux1, toTorre, fromTorre, aux2);
    }

    // função auxiliar para resolver as Torres de Hanoi com três torres (clássico)
    static void towerOfHanoi3(int n, int base, int fromTorre, int toTorre, int auxTorre) {
        if (n == 0) return;

        if (n == 1) {
            recordMove(base, toTorre);
            return;
        }

        // Mover n-1 discos para a torre auxiliar
        towerOfHanoi3(n - 1, base, fromTorre, auxTorre, toTorre);

        // Mover o disco maior para a torre de destino
        recordMove(base + n - 1, toTorre);

        // Mover os n-1 discos da torre auxiliar para a torre de destino
        towerOfHanoi3(n - 1, base, auxTorre, toTorre, fromTorre);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]); // Número de discos
        boolean verbose = args.length > 1 && args[1].equals(
                "."); // Verifica se o modo verbose está ativado

        Hanoi4 hanoi = new Hanoi4(n); // Inicializa a memoização de U[k]

        // Chama o metodo para resolver as Torres de Hanoi com as torres 0, 1, 2, e 3
        // Configuração das torres:
        // fromTorre = 0 (origem)
        // toTorre = 1 (destino)
        // aux1 = 2 (primeira auxiliar)
        // aux2 = 3 (segunda auxiliar)

        towerOfHanoi(n, 1, 0, 1, 3, 2);

        if (verbose) {
            System.out.println(moveCount + " moves");
        }

        System.out.println(moveBuffer.trim());
    }
}





/******************************************************************************
 *
 * CCM0118 COMPUTAÇÃO I
 * Aluno: Bruno Kleine Mollica
 * Numero USP: 14562470
 * Tarefa: EP 03 - Pt. 2
 * Data: 03/11/2024
 *
 * Baseado em CollidingBallsWithGravity.java, exercícios em sala, monitoria e Sandbox das aulas.
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

import java.awt.Color;

public class CollidingBallsDiskWithGravity {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double dt = 1.0;
        double RADIUS = 5.0;
        double G = 0.2;  // gravidade

        int SIZE = 512;
        double xc = SIZE / 2.0;
        double yc = SIZE / 2.0;
        double circleRadius = SIZE / 2.0 - RADIUS;

        // inicializa posições e velocidades aleatórias dentro do círculo
        double[] px = new double[N];
        double[] py = new double[N];
        double[] vx = new double[N];
        double[] vy = new double[N];
        Color[] color = new Color[N];
        for (int i = 0; i < N; i++) {
            // posição aleatória dentro do círculo
            double angle = 2 * Math.PI * Math.random();
            double radius = circleRadius * Math.sqrt(Math.random());
            px[i] = xc + radius * Math.cos(angle);
            py[i] = yc + radius * Math.sin(angle);

            // velocidade aleatória
            vx[i] = 10 * Math.random() - 5;
            vy[i] = 10 * Math.random() - 5;

            // cor aleatória
            color[i] = Color.getHSBColor((float) Math.random(), 1.0f, 1.0f);
        }

        StdDraw.setXscale(-10, SIZE + 10);
        StdDraw.setYscale(-10, SIZE + 10);
        StdDraw.enableDoubleBuffering();

        while (true) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledSquare(SIZE / 2, SIZE / 2, SIZE / 2);

            // detecta colisão com a borda do círculo e reflete a velocidade
            for (int i = 0; i < N; i++) {
                double xNew = px[i] + vx[i] * dt;
                double yNew = py[i] + vy[i] * dt;
                double dx = xNew - xc;
                double dy = yNew - yc;
                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist + RADIUS > circleRadius) {
                    // vetor normal naa colisão
                    double nx = dx / dist;
                    double ny = dy / dist;

                    // vetor velocidade
                    double vDotN = vx[i] * nx + vy[i] * ny;

                    // reflete a velocidade
                    vx[i] = vx[i] - 2 * vDotN * nx;
                    vy[i] = vy[i] - 2 * vDotN * ny;
                }
            }

            // detecta colisão com outras partículas
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    double dx = (px[i] - px[j]) + dt * (vx[i] - vx[j]);
                    double dy = (py[i] - py[j]) + dt * (vy[i] - vy[j]);
                    double distance = Math.sqrt(dx * dx + dy * dy);

                    // se houver colisão, troca as velocidades
                    if (distance <= 2 * RADIUS) {
                        double tempx = vx[i];
                        double tempy = vy[i];
                        vx[i] = vx[j];
                        vy[i] = vy[j];
                        vx[j] = tempx;
                        vy[j] = tempy;
                        break;
                    }
                }
            }

            // atualiza as posições e velocidades
            for (int i = 0; i < N; i++) {
                // Aplica a gravidade
                vy[i] -= G;

                // Atualiza as posições
                px[i] = px[i] + vx[i] * dt;
                py[i] = py[i] + vy[i] * dt;

                // Garante que a bola permaneça dentro do círculo após a atualização da posição
                double dx = px[i] - xc;
                double dy = py[i] - yc;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if (dist + RADIUS > circleRadius) {
                    // Move a bola de volta para dentro do círculo
                    double overlap = dist + RADIUS - circleRadius;
                    px[i] -= overlap * dx / dist;
                    py[i] -= overlap * dy / dist;
                }
            }

            // desenha as bolas
            for (int i = 0; i < N; i++) {
                StdDraw.setPenColor(color[i]);
                StdDraw.filledCircle(px[i], py[i], RADIUS);
            }

            // desenha a borda
            StdDraw.setPenColor(StdDraw.CYAN);
            StdDraw.circle(xc, yc, circleRadius);

            StdDraw.show();
            StdDraw.pause(30);
        }
    }
}



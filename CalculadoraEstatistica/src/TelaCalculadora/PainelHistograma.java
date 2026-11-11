package TelaCalculadora;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;
import java.util.Arrays;

public class PainelHistograma extends JPanel {
    private static final long serialVersionUID = 1L;
    private double[] dados;

    public PainelHistograma(double[] dados) {
        this.dados = dados;
        // Aumentamos a largura e altura para acomodar os rótulos detalhados
        setPreferredSize(new Dimension(700, 450));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (dados == null || dados.length == 0) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int largura = getWidth();
        int altura = getHeight();
        
        int margemEsquerda = 60;
        int margemDireita = 30;
        int margemSuperior = 40;
        int margemInferior = 90; // Mais espaço embaixo para os intervalos

        int larguraUtil = largura - (margemEsquerda + margemDireita);
        int alturaUtil = altura - (margemSuperior + margemInferior);

        // Número de barras (máximo de 5 para ficar limpo)
        int numBarras = Math.min(dados.length, 5);
        if (numBarras < 1) numBarras = 1;

        double min = Arrays.stream(dados).min().orElse(0);
        double max = Arrays.stream(dados).max().orElse(1);
        double amplitude = (max - min);
        if (amplitude == 0) amplitude = 1;

        double larguraIntervalo = amplitude / numBarras;

        int[] frequencias = new int[numBarras];
        double[] limitesInferiores = new double[numBarras];
        double[] limitesSuperiores = new double[numBarras];

        // Calcula os limites de cada classe/intervalo
        for (int i = 0; i < numBarras; i++) {
            limitesInferiores[i] = min + (i * larguraIntervalo);
            limitesSuperiores[i] = min + ((i + 1) * larguraIntervalo);
        }

        // Distribui os dados nas classes corretas
        for (double v : dados) {
            int idx = (int) ((v - min) / amplitude * numBarras);
            if (idx >= numBarras) {
                idx = numBarras - 1; // Garante que o valor máximo entre na última barra
            }
            frequencias[idx]++;
        }

        int maiorFrequencia = 1;
        for (int f : frequencias) {
            if (f > maiorFrequencia) maiorFrequencia = f;
        }

        int larguraBarra = larguraUtil / numBarras;
        int eixoYPos = altura - margemInferior;

        // Título do Gráfico
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(new Font("Tahoma", Font.BOLD, 13));
        g2d.drawString("Distribuição de Frequência (Histograma)", margemEsquerda, margemSuperior - 15);

        // Linhas de grade horizontais e marcações do Eixo Y
        g2d.setFont(new Font("Tahoma", Font.PLAIN, 10));
        int totalLinhas = 5;
        for (int i = 0; i <= totalLinhas; i++) {
            int yVal = eixoYPos - (int) ((double) i / totalLinhas * alturaUtil);
            int freqVal = (int) Math.round((double) i / totalLinhas * maiorFrequencia);
            
            // Linha de grade cinza clara
            if (i > 0 && i < totalLinhas) {
                g2d.setColor(new Color(230, 230, 230));
                g2d.drawLine(margemEsquerda, yVal, largura - margemDireita, yVal);
            }
            
            // Número da frequência no Eixo Y
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawString(String.valueOf(freqVal), margemEsquerda - 25, yVal + 4);
        }

        // Eixos principais (X e Y)
        g2d.setColor(Color.BLACK);
        g2d.drawLine(margemEsquerda, eixoYPos, largura - margemDireita, eixoYPos); // Eixo X
        g2d.drawLine(margemEsquerda, margemSuperior, margemEsquerda, eixoYPos); // Eixo Y

        // Desenhar Barras e Rótulos
        for (int i = 0; i < numBarras; i++) {
            int hBarra = (int) (((double) frequencias[i] / maiorFrequencia) * alturaUtil);
            int x = margemEsquerda + (i * larguraBarra);
            int y = eixoYPos - hBarra;

            // Retângulo da barra
            g2d.setColor(new Color(70, 130, 180)); // Azul Aço
            g2d.fillRect(x + 6, y, larguraBarra - 12, hBarra);

            // Borda da barra
            g2d.setColor(Color.DARK_GRAY);
            g2d.drawRect(x + 6, y, larguraBarra - 12, hBarra);

            // Valor da frequência exata no topo da barra
            if (frequencias[i] > 0) {
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Tahoma", Font.BOLD, 11));
                g2d.drawString(String.valueOf(frequencias[i]), x + (larguraBarra / 2) - 6, y - 6);
            }

            // Rótulo do intervalo no Eixo X (Ex: "1.5 a 1132.8")
            String intervaloTexto = String.format("%.1f a %.1f", limitesInferiores[i], limitesSuperiores[i]);
            
            g2d.setFont(new Font("Tahoma", Font.PLAIN, 10));
            g2d.setColor(Color.BLACK);
            
            // Criamos uma cópia do contexto gráfico para rotacionar o texto das legendas (evita sobreposição)
            Graphics2D g2dRotulo = (Graphics2D) g2d.create();
            g2dRotulo.translate(x + (larguraBarra / 2), eixoYPos + 12);
            g2dRotulo.rotate(Math.toRadians(30)); // Inclina o texto em 30 graus
            g2dRotulo.drawString(intervaloTexto, 0, 0);
            g2dRotulo.dispose();
        }
    }
}
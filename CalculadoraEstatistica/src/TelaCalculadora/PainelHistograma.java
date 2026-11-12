package TelaCalculadora;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.Arrays;

public class PainelHistograma extends JFrame {

    private static final long serialVersionUID = 1L;

    public PainelHistograma(double[] numeros) {
        setTitle("Histograma de Frequência");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultCategoryDataset dados = new DefaultCategoryDataset();

        if (numeros != null && numeros.length > 0) {
            double[] copia = numeros.clone();
            Arrays.sort(copia);

            int numUnicos = 1;
            for (int i = 1; i < copia.length; i++) {
                if (copia[i] != copia[i - 1]) {
                    numUnicos++;
                }
            }

            double[] valoresUnicos = new double[numUnicos];
            int[] frequencias = new int[numUnicos];

            int indiceAtual = 0;
            valoresUnicos[0] = copia[0];
            frequencias[0] = 1;

            for (int i = 1; i < copia.length; i++) {
                if (copia[i] == copia[i - 1]) {
                    frequencias[indiceAtual]++;
                } else {
                    indiceAtual++;
                    valoresUnicos[indiceAtual] = copia[i];
                    frequencias[indiceAtual] = 1;
                }
            }

            for (int i = 0; i < valoresUnicos.length; i++) {
                String rotulo = String.valueOf(valoresUnicos[i]);
                int qtd = frequencias[i];
                
                dados.addValue(qtd, "Frequência", rotulo);
            }
        }

        JFreeChart grafico = ChartFactory.createBarChart(
                "Histograma de Frequência dos Dados",
                "Valores Registrados",
                "Frequência",
                dados
        );

        ChartPanel painelGrafico = new ChartPanel(grafico);
        add(painelGrafico);
    }
}
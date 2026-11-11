package TelaCalculadora;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.Arrays;

public class TelaCalculadora extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextArea txtAreaNumeros;
	private JLabel lblResultado;
	private JComboBox<String> comboBoxOperacao;
	
	private Estatistica estatistica = new Estatistica();
	
	private double[] numerosRegistrados = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCalculadora frame = new TelaCalculadora();
					frame.setVisible(true);
                    frame.setResizable(false); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCalculadora() {
		setTitle("Calculadora Estatística");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 320);
		getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("CALCULADORA ESTATÍSTICA");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTitulo.setBounds(133, 11, 181, 29);
		getContentPane().add(lblTitulo);
		
		JLabel lblDigitar = new JLabel("Digite os números (separados por vírgula ou espaço):");
		lblDigitar.setBounds(10, 45, 355, 14);
		getContentPane().add(lblDigitar);
		
		txtAreaNumeros = new JTextArea();
		txtAreaNumeros.setBounds(10, 70, 414, 64);
		getContentPane().add(txtAreaNumeros);
		
		JLabel lblOperacao = new JLabel("Escolha a operação abaixo:");
		lblOperacao.setBounds(10, 145, 160, 14);
		getContentPane().add(lblOperacao);
		
		String[] opcoes = {"Média", "Moda", "Mediana", "Q1", "Q3", "Variância", "Coeficiente de Variação", "Desvio-Padrão"};
		comboBoxOperacao = new JComboBox<>(opcoes);
		comboBoxOperacao.setBounds(10, 161, 160, 22);
		getContentPane().add(comboBoxOperacao);
		
		lblResultado = new JLabel("Resultado: ");
		lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblResultado.setBounds(10, 205, 414, 30);
		getContentPane().add(lblResultado);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(
            e -> {
                txtAreaNumeros.setText("");
                comboBoxOperacao.setSelectedIndex(0);
                lblResultado.setText("Resultado --");
                numerosRegistrados = null;
            }
        );
        btnLimpar.setBounds(113, 246, 89, 23);
		getContentPane().add(btnLimpar);
		
		JButton btnHelp = new JButton("?");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
					"Instruções:\n1. Digite os números separados por vírgula ou espaço."
					+ "\n(Exemplo: 1, 2, 3.3, 4, 5)\n2. Clique em 'Registrar' para validar os dados."
					+ "\n3. Escolha a operação e clique em 'Calcular'."
					+ "\n4. Após todas as etapas acima, é possível gerar um gráfico de histograma ao clicar no botão 'Gráfico'."
					+ "\n5. Para registrar novos dados, clique no botão 'Limpar' (ou remova manualmente), digite os novos dados"
					+ "\ne clique novamente no botão 'Registrar' para validar os novos valores."
					+ "\n\nEste projeto foi realizado por:"
					+ "\nÁlvaro Eugenio - 202610002"
					+"\nKaiky Tereza - 202610079",
					
					"Ajuda", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnHelp.setBounds(10, 246, 54, 23);
		getContentPane().add(btnHelp);
		
		// Botão REGISTRAR: Valida o texto, conta os elementos e mostra o resumo
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String numerosTexto = txtAreaNumeros.getText().trim();
					
					if (numerosTexto.isEmpty()) {
						JOptionPane.showMessageDialog(null, "O espaço de números está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					// Separa os elementos por vírgula ou espaços
					String[] partes = numerosTexto.split("[,\\s]+");
					double[] tempNumeros = new double[partes.length];
					
					for (int i = 0; i < partes.length; i++) {
						tempNumeros[i] = Double.parseDouble(partes[i]);
					}
					
					// Salva na variável global da classe
					numerosRegistrados = tempNumeros;
					
					// Monta a mensagem com a quantidade e os valores
					StringBuilder resumo = new StringBuilder();
					resumo.append("Quantidade de dados digitados: ").append(numerosRegistrados.length).append("\n\nValores:\n");
					for (int i = 0; i < numerosRegistrados.length; i++) {
						resumo.append("[").append(i + 1).append("] ").append(numerosRegistrados[i]).append("\n");
					}
					resumo.append("\nSe houver algo errado, volte ao espaço, corrija e clique em 'Registrar' novamente.");
					
					JOptionPane.showMessageDialog(null, resumo.toString(), "Dados Registrados com Sucesso", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, 
						"Erro de formatação! Certifique-se de digitar apenas números válidos (use ponto para decimais).\nRecomendação: apague, corrija o erro e digite novamente.", 
						"Erro de Entrada", JOptionPane.ERROR_MESSAGE);
					numerosRegistrados = null; // Reseta caso dê erro
				}
			}
		});
		btnRegistrar.setBounds(335, 161, 89, 23);
		getContentPane().add(btnRegistrar);
		
		// Botão CALCULAR: Executa a operação escolhida usando os dados já registrados
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (numerosRegistrados == null || numerosRegistrados.length == 0) {
					JOptionPane.showMessageDialog(null, "Por favor, clique primeiro no botão 'Registrar' os números!", "Aviso", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String operacaoEscolhida = (String) comboBoxOperacao.getSelectedItem();
				double resultado = 0;
				
				
				switch (operacaoEscolhida) {
					case "Média":
						resultado = estatistica.media(numerosRegistrados);
						lblResultado.setText(String.format("Resultado (Média): %.2f", resultado));
						break;
						
					case "Mediana":
						resultado = estatistica.mediana(numerosRegistrados);
						lblResultado.setText(String.format("Resultado (Mediana): %.2f", resultado));
						break;
					
					case "Moda":
						resultado = estatistica.moda(numerosRegistrados);
						lblResultado.setText(String.format("Resultado (Moda): %.2f", resultado));
						break;
						
					case "Q1":
						resultado = estatistica.q1(numerosRegistrados);
						lblResultado.setText(String.format("Resultado (Q1): %.2f", resultado));
						break;
					
					case "Q3":
						resultado = estatistica.q3(numerosRegistrados);
						lblResultado.setText(String.format("Resultado (Q3): %.2f", resultado));
						break;
					
					case "Variância":
						resultado = estatistica.variancia(numerosRegistrados);
						lblResultado.setText(String.format("Resultado (Variância): %.2f", resultado));
						break;
					
					case "Coeficiente de Variação":
						resultado = estatistica.coeficienteV(estatistica.desvioPadrao(estatistica.variancia(numerosRegistrados)), estatistica.media(numerosRegistrados));
						lblResultado.setText(String.format("Resultado (Coeficiente de Variação): %.2f", resultado));
						break;

					
					case "Desvio-Padrão":
						resultado = estatistica.desvioPadrao(estatistica.variancia(numerosRegistrados));
						lblResultado.setText(String.format("Resultado (Desvio-Padrão): %.2f", resultado));
						break;

					default:
						lblResultado.setText("Operação '" + operacaoEscolhida + "' em desenvolvimento.");
						break;
				}
			}
		});
		btnCalcular.setBounds(335, 246, 89, 23);
		getContentPane().add(btnCalcular);
		
		JButton btnGrafico = new JButton("Gráfico");
		btnGrafico.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (numerosRegistrados == null || numerosRegistrados.length == 0) {
		            JOptionPane.showMessageDialog(null, "Por favor, clique primeiro no botão 'Registrar' os números!", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
		        // Criando a janela do gráfico
		        JFrame janelaGrafico = new JFrame("Histograma dos Dados");
		        janelaGrafico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas a janela do gráfico, não o programa todo
		        
		        // Instancia a classe que você criou no outro arquivo
		        PainelHistograma painel = new PainelHistograma(numerosRegistrados);
		        
		        janelaGrafico.add(painel);
		        janelaGrafico.pack();
		        janelaGrafico.setLocationRelativeTo(null); // Centraliza na tela
		        janelaGrafico.setVisible(true);
		    }
		});
		btnGrafico.setBounds(225, 246, 89, 23);
		getContentPane().add(btnGrafico);
	}
}